package com.migros.courierService.observerUtil;

import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.dto.StoreDto;
import com.migros.courierService.util.ObserverUtil;
import org.antlr.stringtemplate.language.ArrayWrappedInList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.util.AutoPopulatingList;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ObserverUtilTest {

    @Autowired
    @InjectMocks
    public ObserverUtil observerUtilTest;

    @SpyBean
    public ObserverUtil observerUtilMock;

    private static List<StoreDto> storeList = new ArrayList<>();
    @BeforeAll
    static void prepareData(){
        StoreDto storeDto = new StoreDto();
        storeDto.setName("Store Test");
        storeList.add(storeDto);
    }
    @Test
    @DisplayName("subscribe - Success")
    public void subscribe_success(){
        List<StoreDto> storeDtoList = new ArrayList();
        observerUtilTest.subscribe(storeDtoList);
    }
    
    @Test
    @DisplayName("notifyStore - Success")
    public void notifyStore_success(){
        CourierDto courierDto = new CourierDto();
        courierDto.setStoreName("Store Test");
        courierDto.setId(Long.valueOf("1"));
        courierDto.setStatus("InStore");
        //when(observerUtilMock.getStoreList()).thenReturn(storeList);
        observerUtilTest.notifyStore(courierDto);
    }



}
