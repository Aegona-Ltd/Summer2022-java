package com.management.InventoryManagement.DTO;

import com.management.InventoryManagement.Entity.LogProductTransaction;
import com.management.InventoryManagement.Entity.Product;
import com.management.InventoryManagement.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransactionDTO {
    private Integer productTransID;
    private Integer amount;
    private Status status;
    private Product product;
    private List<LogProductTransaction> logProductTransaction;
}
