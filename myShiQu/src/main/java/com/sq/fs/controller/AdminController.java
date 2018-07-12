package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.Admin;
import com.sq.fs.service.AdminService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/18.
 */
@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ResponseBody
    @RequestMapping("/add")
    public R add(@RequestBody Admin admin){


//        if(file!=null&&file.getOriginalFilename()!=null&&file.getOriginalFilename().length()>0){
//
//            String oldFilename = file.getOriginalFilename();
//
//            String dex = oldFilename.substring(oldFilename.indexOf(".")+1);
//            List<String> dexs=new ArrayList<>();
//            dexs.add("jpg");
//            dexs.add("png");
//            if (!dexs.contains(dex)) {
////                System.out.println("不支持的文件格式");
//                return R.error("不支持的文件格式");
//            }
//            String newFilename= UUID.randomUUID().toString()+oldFilename.substring(oldFilename.lastIndexOf("."));
//            String fileAdress="D:\\shiqu\\picture\\";
//            File file2=new File(fileAdress);
//            if(!file2.exists()){
//                file2.mkdirs();
//            }
//            File file1=new File(fileAdress+newFilename);
//
//            try {
//                file.transferTo(file1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            admin.setPhotoPath("/picture/"+newFilename);
//            model.addAttribute("admin","/picture/"+newFilename);
//            System.out.println(admin.toString());
//        }

        List<Admin> adminList = adminService.queryList();
        for (Admin admin1 : adminList) {
            if(admin.getJobNum().equals(admin1.getJobNum())){
                return R.error("工号已存在");
            }
        }
        Md5Hash md5Hash = new Md5Hash("123456", admin.getJobNum(), 1024);
        String password=md5Hash.toString();
        admin.setPassword(password);
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
        Integer[] ids = paramters.get("idList");
        adminService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@PathVariable Integer id,@RequestBody  Admin admin){

        adminService.update(id, admin);
        return R.ok("更新成功");
    }


    @ResponseBody
    @RequestMapping("/login")
    public R login(@RequestBody Admin admin){
//        String adminname=admin.getJobNum();
//        String password=admin.getPassword();
//
////        Boolean rememberMe=false;
////        if("true".equals(rememberMeString)){
////            rememberMe=true;
////        }
//
//
//
//        Subject subject = ShiroUtils.getSubject();
//        CustomizedToken customizedToken = new CustomizedToken(adminname, password, ADMIN_LOGIN_TYPE);
//
//        subject.login(customizedToken);
//        Admin admin1 = adminService.login(adminname, password);
//        return R.ok().put("data",admin1);

        String adminName=admin.getJobNum();
        String adminPwd=admin.getPassword();
        System.out.println(adminName+"........"+adminPwd);
        Md5Hash md5Hash = new Md5Hash("123456", admin.getJobNum(), 1024);
        adminPwd=md5Hash.toString();
        Admin admin2 = adminService.login(adminName, adminPwd);
        if(admin2!=null){

            if(adminPwd.equals(admin2.getPassword())) {
                return R.ok("登录成功").put("data", admin2);
            }
        }
        return R.error("用户名或密码错误");
    }

//    @ResponseBody
//    @RequestMapping("/up/{id}")
//    public R update(@PathVariable Integer id){
//        AdminService adminService=new AdminServiceImpl();
//
//        Admin admin = adminService.queryById(id);
//        Admin admin=new Admin();
//        admin.setJobNum(admin.getJobNum());
//        admin.setDate(admin.getDate());
//        admin.setName(admin.getName());
//        admin.setPassword(admin.getPassword());
//        admin.setPosition(admin.getPosition());
//        admin.setSection(admin.getSection());
//        //admin.setPhotoPath(admin.getPhotoPath());
//        adminService.save(admin);
//        Integer i=admin.getId();
//        Integer[] integers={i};
//        adminService.deleteBatch(integers);
//        return R.ok("更新成功");
//    }
    @ResponseBody
    @RequestMapping("/pwdchange/{id}")
    public R updatePassWord(@RequestBody Admin admin,@PathVariable Integer id){

//        Admin admin1 = adminService.queryById(id);
        String password = admin.getPassword();
        if(password==null&&password.equals("")){

            return R.error("密码不能为空");
        }
        Md5Hash md5Hash = new Md5Hash("123456", admin.getJobNum(), 1024);
        password=md5Hash.toString();
       
        adminService.updatePassWord(id,password);
        return R.ok("更改密码成功");
    }
}
