package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.DTO.ProductDTO;
import com.management.InventoryManagement.Payload.Response.ObjectResponse;
import com.management.InventoryManagement.Service.ProductService;
import com.management.InventoryManagement.Utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private Convert convert;

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody ProductDTO productDTO){
        ProductDTO product = productService.insertProduct(productDTO);
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(), "Insert successfully",
                true, Collections.singletonList(product)));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO){
        ProductDTO product = productService.insertProduct(productDTO);
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(), "Update successfully",
                true, Collections.singletonList(product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
       productService.deleteProduct(id);
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(), "Delete product successfully",
                true, null));
    }

    @GetMapping("/isDeleted")
    public ResponseEntity<?> getProductIsDeleted(){
        List<ProductDTO> products = productService.findProductIsDeleted();
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(),"successfully",true, products));
    }

    @GetMapping("/isNoDeleted")
    public ResponseEntity<?> getProductIsNoDeleted(){
        List<ProductDTO> products = productService.findProductIsNoDeleted();
        return ResponseEntity.ok(new ObjectResponse(HttpStatus.OK.value(),"successfully",true, products));
    }

}
