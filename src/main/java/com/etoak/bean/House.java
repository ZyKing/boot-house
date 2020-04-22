package com.etoak.bean;

import lombok.Data;

@Data
public class House {
    private Integer id;
    private Integer province;
    private Integer city;
    private Integer area;
    private String areaName;
    private String rentMode;
    private String orientation;
    private String houseType;
    private Integer rental;
    private String address;
    private String pic;
    private String publishTime;

}
