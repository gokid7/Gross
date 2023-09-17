package com.migros.registerService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.registerService.dto.RegisterModelDto;
import com.migros.registerService.model.RegisterModel;
import com.migros.registerService.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;
    private ObjectMapper objectMapper;

    public RegisterService(RegisterRepository registerRepository, ObjectMapper objectMapper) {
        this.registerRepository = registerRepository;
        this.objectMapper = objectMapper;
    }

    public List<RegisterModel> findByCourierId(Long courierId){
        return registerRepository.findByCourierId(courierId);
    }

    public void registerCourier(RegisterModelDto modelDto){
        RegisterModel registerModel = objectMapper.convertValue(modelDto,RegisterModel.class);
        registerModel.setTimestamp(new Timestamp(System.currentTimeMillis()));
        registerRepository.save(registerModel);
    }

}
