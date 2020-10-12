package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.pojo.Standard;
import com.kgc.service.StandardService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @Resource
    StandardService standardService;

    @RequestMapping("/")
    public String index(@RequestParam(value = "zhname", required = false, defaultValue = "") String zhname,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, Model model) {

        Integer pageSize = 3;
        List<Standard> select = standardService.select(zhname, pageSize, pageNum);
        PageInfo<Standard> pageInfo = new PageInfo<>(select);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("zhname", zhname);
        return "index";
    }

    @RequestMapping("/del")
    public String del(String idlist, HttpSession session) {
        String[] split = idlist.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        int i = standardService.delBylist(list);
        if (i > 0) {
            session.setAttribute("success", "删除成功");
        } else {
            session.setAttribute("success", "删除失败");
        }
        return "redirect:/";
    }

    @RequestMapping("/toadd")
    public String toadd() {
        return "add";
    }

    @RequestMapping("/doadd")
    public String doadd(Standard standard, MultipartFile avatar_pic, HttpServletRequest request, HttpSession session, Model model) {
        String realPath = request.getServletContext().getRealPath("/static/images/");
        //旧名称
        String originalFilename = avatar_pic.getOriginalFilename();
        if (originalFilename.isEmpty()) {
            model.addAttribute("error", "请选择附件");
            return "add";
        }
        //扩展名
        String extension = FilenameUtils.getExtension(originalFilename);
        //新名称
        String newFileName = (System.currentTimeMillis()) + (RandomUtils.nextInt(1000000)) + "_text" + "." + extension;
        File file = new File(realPath, newFileName);
        try {
            avatar_pic.transferTo(file);
            standard.setPackagePath("/static/images/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int add = standardService.add(standard);
        if (add > 0) {
            session.setAttribute("success", "添加成功");
        } else {
            session.setAttribute("success", "添加失败");
        }
        return "redirect:/";
    }

    @RequestMapping("/selstdNum")
    @ResponseBody
    public String selstdNum(String stdNum) {
        List<Standard> standards = standardService.selectBystdNum(stdNum);
        if (standards.size() > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/toupd")
    public String toupd(Integer id, Model model) {
        Standard standard = standardService.selByid(id);
        model.addAttribute("standard", standard);
        return "upd";
    }

    @RequestMapping("/doupd")
    public String doupd(Standard standard, MultipartFile avatar_pic, HttpServletRequest request, HttpSession session, Model model) {
        String realPath = request.getServletContext().getRealPath("/static/images/");
        //旧名称
        String originalFilename = avatar_pic.getOriginalFilename();
        if (originalFilename.isEmpty() == false) {
            //扩展名
            String extension = FilenameUtils.getExtension(originalFilename);
            //新名称
            String newFileName = (System.currentTimeMillis()) + (RandomUtils.nextInt(1000000)) + "_text" + "." + extension;
            File file = new File(realPath, newFileName);
            try {
                avatar_pic.transferTo(file);
                standard.setPackagePath("/static/images/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int upd = standardService.upd(standard);
        if (upd > 0) {
            session.setAttribute("success", "修改成功");
        } else {
            session.setAttribute("success", "修改失败");
        }
        return "redirect:/";
    }

    @RequestMapping("doxz")
    @ResponseBody
    public void download(String filename, HttpServletRequest request, HttpServletResponse response) {
        //获取路径
        String realPath = request.getServletContext().getRealPath("");
        //封装file对象
        File file = new File(realPath, filename);
        //设置响应类型
        response.setContentType("application/x-msdownload");
        //设置文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //执行下载
        try {
            //file.toPath()要下载的文件路径 ，响应的输出流
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
