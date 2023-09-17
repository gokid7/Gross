package com.migros.courierService.util;

import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.dto.StoreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObserverUtil {
    private List<StoreDto> storeList = new ArrayList<>();
    public void subscribe(List<StoreDto> stores){
        storeList.addAll(stores);
    }

    public void notifyStore(CourierDto courier){
        storeList.forEach(store -> {
            if (store.getName().equalsIgnoreCase(courier.getStoreName())) {
                System.out.println(courier.getId() + " nolu kurye "+ store.getName() +" mağazasına giriş yaptı. Kurye statusü : " + courier.getStatus());
            }
        });
    }

}
