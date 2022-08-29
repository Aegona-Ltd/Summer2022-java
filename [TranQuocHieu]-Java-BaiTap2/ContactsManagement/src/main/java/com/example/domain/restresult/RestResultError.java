package com.example.domain.restresult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResultError{
    private Integer result;
    private String message;
    private Map<String, String> error;
}
