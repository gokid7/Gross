package com.migros.courierService.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierBaseResponse <T>{
    @JsonProperty("result")
    private T result;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errorResponse")
    private ErrorResponse errorResponse;
}
