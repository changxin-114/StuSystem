package com.kgc.service.impl;


import com.kgc.mapper.StandardMapper;
import com.kgc.pojo.Standard;
import com.kgc.pojo.StandardExample;
import com.kgc.service.StandardServicecx;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("standardService")
public class StandardServicecxImpl implements StandardServicecx {
    @Resource
    StandardMapper standardMapper;

    @Override
    public List<Standard> selectAll(String zhname, int pageIndex, int pageSize) {
        StandardExample standardExample=new StandardExample();
        StandardExample.Criteria criteria = standardExample.createCriteria();
        if(zhname!=null){
            criteria.andZhnameLike("%"+zhname+"%");
        }else{
            return standardMapper.selectByExample(null);
        }
        return standardMapper.selectByExample(standardExample);
    }
}
