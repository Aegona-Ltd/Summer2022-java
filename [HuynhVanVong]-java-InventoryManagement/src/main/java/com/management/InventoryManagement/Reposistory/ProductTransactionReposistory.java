package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.ProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTransactionReposistory extends JpaRepository<ProductTransaction, Integer> {
}
