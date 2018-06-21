package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.Admin;
import com.sq.fs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/18.
 */
@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/add")
    public R add(Admin admin,
                 @RequestParam("file1") MultipartFile file,
                 Model model){

        System.out.println(admin.toString());
        System.out.println(file);
        if(file!=null&&file.getOriginalFilename()!=null&&file.getOriginalFilename().length()>0){

            String oldFilename = file.getOriginalFilename();
            String newFilename= UUID.randomUUID().toString()+oldFilename.substring(oldFilename.lastIndexOf("."));
            String fileAdress="D:\\shiqu\\picture\\";
            File file1=new File(fileAdress+newFilename);

            try {
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }


            admin.setPhotoPath("/picture/"+newFilename);
            model.addAttribute("admin","/picture/"+newFilename);
            System.out.println(admin.toString());
        }
        adminService.save(admin);
        return R.ok("添加成功");
    }

    @ResponseBody
    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Admin admin = adminService.queryById(id);
        if(admin==null){
            R.error("查无此人");
        }
        return R.ok().put("data",admin);
    }

    @ResponseBody
    @RequestMapping("/show")
    public R show(){

        List<Admin> adminList = adminService.queryList();
        return R.ok().put("data",adminList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

        System.out.println(paramters);
        Integer[] ids = paramters.get("id");
        adminService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@PathVariable Integer id,  Admin admin){

        adminService.update(id, admin);
        return R.ok("更新成功");
    }




    @ResponseBody
    @RequestMapping("/login")
    public R login(Admin admin,  HttpSession session){
        String adminName=admin.getJobNum();
        String adminPwd=admin.getPassword();
        System.out.println(adminName+"........"+adminPwd);
        Admin admin2 = adminService.login(adminName, adminPwd);
        if(admin!=null){
            session.setAttribute("admin",admin2);
            return R.ok("登录成功");
        }
        return R.error("登录失败");
    }


}
