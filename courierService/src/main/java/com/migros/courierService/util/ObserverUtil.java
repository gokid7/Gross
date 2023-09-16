package com.migros.courierService.util;

import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.dto.StoreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObserverUtil {

    public void subscribe(StoreDto store, CourierDto courier){
        List<StoreDto> storeList = new ArrayList<>();
        storeList.addAll(courier.getStores() != null ? courier.getStores() : new ArrayList<>());
        storeList.add(store);
        courier.setStores(storeList);
    }

    public void notifyStore(CourierDto courier){
        System.out.println(courier.getId() + " nolu kurye "+ courier.getStoreName() +" mağazasına giriş yaptı. Kurye statusü : " + courier.getStatus());
    }

}
