package com.kgc.springssm.service;

import com.kgc.springssm.pojo.Standard;
import com.kgc.springssm.pojo.StandardExample;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-10-10 20:16
 */
public interface StandardService {
    List<Standard> selectByExample(StandardExample example);
    int add(Standard standard);

    Standard selectById(int id);

    void upd(Standard standard);

    int del(int id);
}

