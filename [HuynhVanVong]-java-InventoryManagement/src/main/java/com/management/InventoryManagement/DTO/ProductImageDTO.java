package com.management.InventoryManagement.DTO;

import com.management.InventoryManagement.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private String name;
    private Product product;
}
