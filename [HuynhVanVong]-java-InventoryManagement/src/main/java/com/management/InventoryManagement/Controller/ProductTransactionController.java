package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.DTO.LogProductTransactionDTO;
import com.management.InventoryManagement.DTO.ProductTransactionDTO;
import com.management.InventoryManagement.Payload.Response.ObjectResponse;
import com.management.InventoryManagement.Payload.Response.ResponseMessage;
import com.management.InventoryManagement.Service.LogProductTransactionService;
import com.management.InventoryManagement.Service.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productTrans")
public class ProductTransactionController {
    @Autowired
    private ProductTransactionService productTransactionService;
    @Autowired
    private LogProductTransactionService logProductTransactionService;

    @PostMapping
    public ResponseEntity<?> insertProductTrans(@RequestBody ProductTransactionDTO productTransactionDTO){
        ProductTransactionDTO productTrans = productTransactionService. insertProductTransaction(productTransactionDTO);

        LogProductTransactionDTO logProductTransactionDTO = new LogProductTransactionDTO();
        logProductTransactionDTO.setUserID(1);
        logProductTransactionDTO.setProductTransID(productTrans.getProductTransID());
        logProductTransactionService.insertLogProductTransaction(logProductTransactionDTO);

        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(),"InsertProductTrans successfully",
                true, Collections.singletonList(productTrans)));
    }

    @PutMapping("{id}")
    ResponseEntity<?> approveEnterStock(@PathVariable Integer id){
        Boolean productTransactionDTO = productTransactionService.updateStatusTrans(id);
        if(productTransactionDTO == true){
            return ResponseEntity.ok(new ResponseMessage("Approve successfully"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("Approved or error"));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<?> getLog(@PathVariable Integer id) {
        List<LogProductTransactionDTO> list = productTransactionService.findLogByTransId(id);
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(),"successfully", true, list));
    }

    // get product no enter stock yet

    @GetMapping("/NoEnterStockYet")
    public ResponseEntity<?> getProductNoEnterStock(){
        return ResponseEntity.ok(productTransactionService.findTransByProductId(1));
    }
}
