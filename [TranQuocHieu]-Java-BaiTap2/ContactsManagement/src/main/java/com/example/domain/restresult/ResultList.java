package com.example.domain.restresult;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResultList {
    private Integer result;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private String sort;
    private List<?> data;
}
