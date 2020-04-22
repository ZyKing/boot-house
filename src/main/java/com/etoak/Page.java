package com.etoak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private int pageNum;
    private int pageSize;
    private List<T> rows;
    private long total;
    private int pageCount;
}
