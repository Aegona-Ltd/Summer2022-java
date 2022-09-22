package com.management.InventoryManagement.Reposistory;

import com.management.InventoryManagement.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountReposistory extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUserNameEquals(String username);
}
