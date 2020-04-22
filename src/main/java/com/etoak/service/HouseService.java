package com.etoak.service;

import com.etoak.Page;
import com.etoak.bean.House;
import com.etoak.bean.HouseVo;

import java.util.List;

public interface HouseService {
    int addHouse(House house);
    Page<HouseVo> queryList(int pageNum,int pageSize,HouseVo houseVo);
}
