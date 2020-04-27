package com.etoak.service;

import com.etoak.bean.Page;
import com.etoak.bean.House;
import com.etoak.bean.HouseVo;

public interface HouseService {
    int addHouse(House house);
    Page<HouseVo> queryList(int pageNum, int pageSize, HouseVo houseVo, String[] rentalList);
}
