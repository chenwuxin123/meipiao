package com.example.practice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2020-05-22 17:37:29
 */
@Data
public class Test implements Serializable {
    private static final long serialVersionUID = -74228051166383152L;
    
    private Integer id;
    
    private String name;
    
    private String dis;
    
    private String time;

    private Long total;

}