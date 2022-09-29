package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Entity.ProductTransaction;
import com.management.InventoryManagement.Payload.Response.ProductTransResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductTransactionService {
    ProductTransactionDTO insertProductTransaction(ProductTransactionDTO productTransaction);
    Boolean updateStatusTrans(Integer id);
    List<LogProductTransactionDTO> findLogByTransId(Integer id);
    List<ProductTransResponse> findTransByProductId(Integer id);
}
