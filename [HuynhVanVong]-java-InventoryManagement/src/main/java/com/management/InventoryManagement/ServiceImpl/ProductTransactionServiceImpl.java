package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Entity.Product;
import com.management.InventoryManagement.Entity.ProductTransaction;
import com.management.InventoryManagement.Entity.Status;
import com.management.InventoryManagement.Payload.Response.ProductTransResponse;
import com.management.InventoryManagement.Reposistory.ProductReposistory;
import com.management.InventoryManagement.Reposistory.ProductTransactionReposistory;
import com.management.InventoryManagement.Service.ProductTransactionService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
            return true;
        }
        return false;
    }

    @Override
    public List<LogProductTransactionDTO> findLogByTransId(Integer id) {
        Optional<ProductTransaction> productTrans = productTransactionReposistory.findById(id);
        if(productTrans.isPresent()) {
            List<LogProductTransactionDTO> listLog = productTrans.get().getLogProductTransaction().stream()
                    .map(log -> convert.toDto(log, LogProductTransactionDTO.class)).collect(Collectors.toList());
            return listLog;
        }
        return null;
    }

    @Override
    public List<ProductTransResponse> findTransByProductId(Integer id) {
        List<ProductTransaction> listTrans = productTransactionReposistory.findAllByStatusStatusID(id);
        List<ProductTransResponse> listResponse = new ArrayList<>();
        for (ProductTransaction o: listTrans) {
            ProductTransResponse p = new ProductTransResponse();
            p.setProductTransID(o.getProductTransID());
            p.setAmount(o.getAmount());
            p.setStatusName(o.getStatus().getStatusName());
            p.setProductName(o.getProductID().getTitle());
            listResponse.add(p);
        }
        return listResponse;
    }
}
