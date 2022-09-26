package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO insertProduct(ProductDTO product);
    ProductDTO updateProduct(ProductDTO product);
    void deleteProduct(Integer id);
    List<ProductDTO> findProductIsDeleted();
    List<ProductDTO> findProductIsNoDeleted();
}
