package com.migros.courierService.service;

import com.migros.courierService.model.redis.RedisCourier;
import com.migros.courierService.model.redis.RedisStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourierServiceTest {
    @Autowired
    @InjectMocks
    public CourierService courierServiceTest;
    @MockBean
    public RedisCourierService redisCourierServiceMock;
    @MockBean
    public RedisStoreService redisStoreServiceMock;

    @Test
    @DisplayName("getTotalTravelDistance - Success")
    public void getTotalTravelDistance_success(){
        RedisCourier mockData = Mockito.mock(RedisCourier.class);
        when(mockData.getTotalDistance()).thenReturn(Double.valueOf("15"));
        when(redisCourierServiceMock.findByCourierId(Mockito.any())).thenReturn(Optional.ofNullable(mockData));

        Double result = courierServiceTest.getTotalTravelDistance(Long.valueOf("1"));
        assertEquals("Success",Double.valueOf("15"),result);
    }

    @Test
    @DisplayName("getTotalTravelDistance - Zero Success")
    public void getTotalTravelDistance_zero_success(){
        when(redisCourierServiceMock.findByCourierId(Mockito.any())).thenReturn(Optional.empty());
        Double result = courierServiceTest.getTotalTravelDistance(Long.valueOf("1"));
        assertEquals("Success",Double.valueOf("0"),result);
    }

    @Test
    @DisplayName("getRedisStore - Success")
    public void getRedisStore_success(){
        List<RedisStore> redisStores = new ArrayList<>();
        when(redisStoreServiceMock.getAllStores()).thenReturn(redisStores);
        redisStores = courierServiceTest.getRedisStore();
        assertNotNull("Success" ,redisStores);
    }


}
