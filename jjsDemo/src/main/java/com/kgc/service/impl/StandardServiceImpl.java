package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.mapper.StandardMapper;
import com.kgc.pojo.Standard;
import com.kgc.pojo.StandardExample;
import com.kgc.service.StandardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StandardServiceImpl implements StandardService {
    @Resource
    StandardMapper standardMapper;

    @Override
    public List<Standard> select(String zhname, Integer pageSize, Integer pageNum) {
        StandardExample example = new StandardExample();
        StandardExample.Criteria criteria = example.createCriteria();
        if (zhname != "" && zhname.isEmpty() == false) {
            criteria.andZhnameLike("%" + zhname + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("std_num asc");
        List<Standard> standards = standardMapper.selectByExample(example);
        return standards;
    }

    @Override
    public int delBylist(List list) {
        StandardExample example = new StandardExample();
        StandardExample.Criteria criteria = example.createCriteria();
        if (list.size() > 0) {
            criteria.andIdIn(list);
        }
        int i = standardMapper.deleteByExample(example);
        return i;
    }

    @Override
    public int add(Standard standard) {
        int insert = standardMapper.insert(standard);
        return insert;
    }

    @Override
    public List<Standard> selectBystdNum(String stdNum) {
        StandardExample example = new StandardExample();
        StandardExample.Criteria criteria = example.createCriteria();
        criteria.andStdNumEqualTo(stdNum);
        List<Standard> standards = standardMapper.selectByExample(example);
        return standards;
    }

    @Override
    public Standard selByid(Integer id) {
        Standard standard = standardMapper.selectByPrimaryKey(id);
        return standard;
    }

    @Override
    public int upd(Standard standard) {
        int i = standardMapper.updateByPrimaryKeySelective(standard);
        return i;
    }
}
