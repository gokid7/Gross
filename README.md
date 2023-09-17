README

Kurye takip uygulaması: kuryenin mağazaya giriş ve yolda işlemleri ve toplam katedilen mesafe bilgisi için , dört adet microservis ile geliştirilmiştir.
Bunlar ;
- [ ] EurekaServer
- [ ] CourierService
- [ ] StoreService
- [ ] RegisterService

İlk olarak EurekaServer çalıştırılır ve ana sunucu olarak diğer microservisler buraya login olurlar. Daha sonra sırasıyla StoreService, RegisterService ve CourierService çalıştırılır.
EurekaServer a bağlanan StoreService ve RegisterService portları “0” olarak tanımlanmıştır. Her defasından rastgele port numarası verilmek istenmiştir. Bunun sebebi proje büyüklüğünde otomatik olarak k8s veya Ocp de yeni pod’ları yaratabilmesi içindir.(CourierService için ise 8090 port u postman üzerinden kolay denenmesi için manuel verilmiştir.)

StoreService; kendi H2 database i vardır ve initialize olduğunda “stores.json” dosyasındaki mağaza bilgileri db ye kayıt edilir. Daha sonra client olarak diğer servislere bu bilgileri verir.

RegisterService; kendi H2 database vardır. Kurye gerekli koşulları sağladığında mağaza kayıt olmak için bu servis ile db sine kayıt atar. Yine client olarak bu bilgiyi diğer microservislere verir.

CourierService; Hem H2 database i hemde cache-memory olarak Redis kullanılır. Kurye takibi endpointine (courierTracking) istek atıldığında StoreService ten mağaza bilgileri alınıp Redis sunucusuna kaydeder. Daha sonra yeni kurye kaydı oluşturup kendi H2 db sine kaydeder. Mevcutta kurye kaydı varsa redisten alıp onu günceller ve toplam mesafe hesabını yapar. Daha sonra mağazaya “100 metre yarıçap” ve “1dk öncesinde giriş yapılmış mı” kontrollerini yapar. Koşullar sağlanıyorsa RegisterService ve Redis sunucusuna yeni kayıt atılır.(Mevcutta varsa güncellenir.) Observer tasarım deseni ile kurye hareketlerini izler ve “Yolda” veya “Mağazada” şeklinde console da bildirim gösterir.

“getTotalTravelDistance” endpoint I ile de , kuryenin toplam katettiği mesafe her defasında Redis te güncellenerek hesaplanır ve getirir.

Unittest ve exceptionhandling sadece CourierService için yazılmıştır. Loglama ise AOP ile tüm microservislere basitçe eklenmiştir.

Swagger ile CourierService için dokumantasyon eklenmiştir.
Redis sunucusu kurulmadan proje doğru çalışmayabilir.

Docker üzerinden çalıştırmak için sırasıyla aşağıdaki komutlar çalıştırılmalıdır:
dockerize.sh komutu çalıştırılıp versiyon bilgisi ve maven goal (clean install) verilmelidir.
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up -d

bu sayede tüm mikroservislerin imajları ve containerları oluşacaktır.

Not: Docker üzerinden çalıştırıldığında eureka kaynaklı bir sorun yaşamaktayım, projeyi intellij üzerinden çalıştırırsanız bir sorun olmayacaktır.
Postman collectionu da ana dizinde mevcuttur.

