package com.team.mange.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResult<T> {
    //数据集合
    private List<T> rows = new ArrayList<T>();
    //数据总条数
    private int total;
}
