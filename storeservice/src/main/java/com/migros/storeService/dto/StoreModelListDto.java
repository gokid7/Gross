package com.migros.storeService.dto;

import com.migros.storeService.model.StoreModel;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StoreModelListDto {

    private ArrayList<StoreModel> stores;
}
