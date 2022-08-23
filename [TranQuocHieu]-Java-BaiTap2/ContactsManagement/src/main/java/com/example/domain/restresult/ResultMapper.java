package com.example.domain.restresult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ResultMapper {

    public static ResultList pageableToResultList(Page<?> pageable) {
        ResultList resultList = new ResultList();
        resultList.setResult(0);
        resultList.setPage(pageable.getNumber()+1);
        resultList.setSize(pageable.getSize());
        resultList.setTotalElements(pageable.getTotalElements());
        resultList.setTotalPages(pageable.getTotalPages());
        resultList.setData(pageable.getContent());
        return resultList;
    }
}
