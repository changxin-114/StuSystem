package com.kgc.service;

import com.kgc.pojo.Standard;

import java.util.List;

public interface StandardServicecx {
    List<Standard> selectAll(String zhname,int pageIndex,int pageSize);

}
