package com.springboot.excel.model;

import java.util.Date;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Zhou Yun
 **/
@Data
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;
}