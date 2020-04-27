package com.etoak.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HouseVo extends House{
    private String rentModeName;
    private String houseTypeName;
    private String orientationName;
    //接收前端的户型参数
    @JsonIgnore
    private String[] houseTypeList;
    @JsonIgnore
    private List<String> orientationList;
    @JsonIgnore
    List<Map<String, Integer>> rentalMapList;

}
