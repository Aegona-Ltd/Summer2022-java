package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Entity.ProductTransaction;

import java.util.List;
import java.util.Optional;

public interface ProductTransactionService {
    ProductTransactionDTO insertProductTransaction(ProductTransactionDTO productTransaction);
    Boolean updateStatusTrans(ProductTransactionDTO productTransaction);
}
