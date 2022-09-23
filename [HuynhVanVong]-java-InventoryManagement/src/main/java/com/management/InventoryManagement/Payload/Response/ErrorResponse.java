package com.management.InventoryManagement.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Integer httpStatus;
    private String message;
    private Boolean isSuccess;
}
