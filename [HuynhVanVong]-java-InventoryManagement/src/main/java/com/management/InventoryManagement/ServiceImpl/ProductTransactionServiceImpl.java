package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Entity.Product;
import com.management.InventoryManagement.Entity.ProductTransaction;
import com.management.InventoryManagement.Entity.Status;
import com.management.InventoryManagement.Reposistory.ProductReposistory;
import com.management.InventoryManagement.Reposistory.ProductTransactionReposistory;
import com.management.InventoryManagement.Service.ProductTransactionService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {
    @Autowired
    private ProductTransactionReposistory productTransactionReposistory;
    @Autowired
    private Convert convert;
    @Autowired
    private ProductReposistory productReposistory;

    @Override
    public ProductTransactionDTO insertProductTransaction(ProductTransactionDTO productTransactionDTO) {
        ProductTransaction productTransaction = convert.toDto(productTransactionDTO, ProductTransaction.class);
        return convert.toDto(productTransactionReposistory.save(productTransaction), ProductTransactionDTO.class);
    }

    @Override
    public Boolean updateStatusTrans(ProductTransactionDTO productTransaction) {
        ProductTransaction oldProductTransaction = productTransactionReposistory.
                findById(productTransaction.getProductTransID()).orElse(null);
        Product oldProduct = productReposistory.findById(productTransaction.getProductID()).orElse(null);
        if(oldProduct != null && oldProductTransaction.getStatus().getStatusID() == 1) {
            oldProduct.setAmount(oldProduct.getAmount()+oldProductTransaction.getAmount());
            oldProductTransaction.setStatus(new Status(2));
            productTransactionReposistory.save(oldProductTransaction);

//            return convert.toDto(oldProductTransaction, ProductTransactionDTO.class);
            return true;
        }
        return false;
    }
}
