package com.beergode.decisionmaker.common.model;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class Page<T> {

    List<T> items;
    Integer pageNumber;
    Integer size;
    Long totalSize;

    public static Page of(Integer pageNumber, Integer size) {
        return new Page(null, pageNumber, size, null);
    }

    public static <T> Page of(List<T> items, Integer pageNumber, Integer size, Long totalSize) {
        return new Page(items, pageNumber, size, totalSize);
    }

    private Page(List<T> items, Integer pageNumber, Integer size, Long totalSize) {
        this.pageNumber = pageNumber;
        this.size = size;
        this.items = items;
        this.totalSize = totalSize;
    }
}
