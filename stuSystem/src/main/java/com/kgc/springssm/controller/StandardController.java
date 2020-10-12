package com.kgc.springssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.springssm.pojo.Standard;
import com.kgc.springssm.pojo.StandardExample;
import com.kgc.springssm.service.StandardService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-10-10 20:17
 */
@Controller
public class StandardController {
    @Resource
    StandardService standardService;
    @RequestMapping("/")
    public String toindex(Model model, String pageIndex, String title){
        int pageNum=1;
        if(pageIndex!=null){
            pageNum=Integer.parseInt(pageIndex);
        }
        int pageSize=3;
        PageHelper.startPage(pageNum,pageSize);
        if(title != null && title.isEmpty() == false){
            StandardExample example=new StandardExample();
            StandardExample.Criteria criteria = example.createCriteria();
            criteria.andZhnameLike("%"+title+"%");
            List<Standard> standards = standardService.selectByExample(example);
            PageInfo pageInfo=new PageInfo(standards);
            model.addAttribute("pageInfo",pageInfo);
            return "index";
        }else{
            PageHelper.startPage(pageNum,pageSize);
            List<Standard> standards = standardService.selectByExample(null);
            PageInfo pageInfo=new PageInfo(standards);
            model.addAttribute("pageInfo",pageInfo);
            return "index";
        }
    }
    @RequestMapping("/down")
    public void down(String filename, HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getServletContext().getRealPath("/static/uploadfiles/");
        File file=new File(realPath,filename);
        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        response.setContentType("application/x-msdownload");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        try {
            Files.copy(file.toPath(), response.getOutputStream());//Files.copy(要下载的文件的路径,响应的输出流)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/toadd")
    public  String toadd(){
        return "add";
    }
    @RequestMapping("/doadd")
    public String doadd(Standard standard, HttpSession session, MultipartFile packagePath1){
        //文件上传
        String realPath = session.getServletContext().getRealPath("/static/uploadfiles/");
        String originalFilename = packagePath1.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFileName=System.currentTimeMillis()+"_"+ RandomUtils.nextInt(1000000)+"_."+extension;
        File file=new File(realPath,newFileName);
        try {
            packagePath1.transferTo(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        standard.setPackagePath(newFileName);
        int i = standardService.add(standard);
        if(i>0){
            session.setAttribute("msg","添加成功！！！");
            return "redirect:/";
        }else {
            session.setAttribute("msg","添加失败！！！");
            return "redirect:/toadd";
        }
    }
    @RequestMapping("/chaupd")
    public  String chaupd(int id, Model model){
        Standard standard=standardService.selectById(id);
        model.addAttribute("standard",standard);
        return "upd";
    }
    @RequestMapping("/doupd")
    public String doupd(Standard standard, HttpSession session, MultipartFile packagePath1){
        //文件上传
        String realPath = session.getServletContext().getRealPath("/static/uploadfiles/");
        String originalFilename = packagePath1.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFileName=System.currentTimeMillis()+"_"+ RandomUtils.nextInt(1000000)+"_."+extension;
        File file=new File(realPath,newFileName);
        try {
            packagePath1.transferTo(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        standard.setPackagePath(newFileName);
     /*   standard.setDevid(devUserSession.getId());
        standard.setCreatedby(devUserSession.getId());
        standard.setCreationdate(new Date());*/
        standardService.upd(standard);
        return "redirect:/";
    }

    @RequestMapping("/dell")
    public String dell(int id,HttpSession session){
        int a=standardService.del(id);
        if(a>0){
            session.setAttribute("error","删除成功");
            return "redirect:/";
        }else {
            session.setAttribute("error","删除失败");
        }
        return "redirect:/";
    }
}
