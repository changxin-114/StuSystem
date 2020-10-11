package com.kgc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.pojo.Standard;
import com.kgc.service.StandardServicecx;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StandardControllercx {
    @Resource
    StandardServicecx standardServicecx;

    @RequestMapping("/selectAll")
    public String selectAll(Model model, String zhname, HttpServletRequest request){
        int Num=1;
        String pageNum=request.getParameter("pageNum");
        if(pageNum!=null){
            Num=Integer.parseInt(pageNum);
        }
        int pageSize=3;
        PageHelper.startPage(Num,pageSize);
        PageHelper.orderBy("std_num asc");
        List<Standard> standards = standardServicecx.selectAll(zhname, Num, pageSize);
        PageInfo pageInfo=new PageInfo(standards);
        model.addAttribute("pageInfo",pageInfo);
        return "indexcx";
    }
}
