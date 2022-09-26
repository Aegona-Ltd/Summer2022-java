package com.management.InventoryManagement.DTO;

import com.management.InventoryManagement.Entity.ProductTransaction;
import com.management.InventoryManagement.Entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogProductTransactionDTO {
    private Integer LogProductTransID;
    private Date dateLog;
    private ProductTransaction productTransaction;
    private UserAccount userAccount;
}
