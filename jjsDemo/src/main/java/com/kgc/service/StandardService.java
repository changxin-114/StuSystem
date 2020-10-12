package com.kgc.service;

import com.kgc.pojo.Standard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public interface StandardService {
    List<Standard> select(String zhname, Integer pageSize, Integer pageNum);
    int delBylist(List list);
    int add(Standard standard);
    List<Standard> selectBystdNum(String stdNum);
    Standard selByid(Integer id);
    int upd(Standard standard);
}
