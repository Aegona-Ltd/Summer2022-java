package com.example.domain.users.model.result;

import com.example.domain.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUsers {
    private Integer page;
    private Page<User> data;
}
