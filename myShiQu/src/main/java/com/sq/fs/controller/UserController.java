package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.User;
import com.sq.fs.service.UserService;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public R add( User user,
                 @RequestParam("file1") MultipartFile file,
                 Model model){

        System.out.println(user.toString());
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


            user.setPhotoPath("/picture/"+newFilename);
            model.addAttribute("url","/picture/"+newFilename);
            System.out.println(user.toString());
        }
        userService.save(user);
        return R.ok("保存成功");
    }

    @ResponseBody
    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        User user = userService.queryById(id);
        if(user==null){
            R.error("查无此人");
        }
        return R.ok().put("data",user);
    }

    @ResponseBody
    @RequestMapping("/show")
    public R show(){

        List<User> userList = userService.queryList();
        return R.ok().put("data",userList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

        System.out.println(paramters);
        Integer[] ids = paramters.get("id");
        userService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@RequestBody User user,@PathVariable Integer id){

        System.out.println(user.toString());
        userService.update(id, user);
        return R.ok("更新成功");
    }

    @ResponseBody
    @RequestMapping("/pwdchange/{id}")
    public R updatePassWord(@RequestBody User user,@PathVariable Integer id){
        String password = user.getPassword();

        userService.updatePassWord(id,password);
        return R.ok("更改密码成功");
    }
//    @RequestMapping("/jsp11")
//    public String jsp11(){
//        return "user";
//    }


    @ResponseBody
    @RequestMapping("/login")
    public R login(@RequestBody User user,  HttpSession session){
        String userName=user.getJobNum();
        String userPwd=user.getPassword();
        System.out.println(userName+"........"+userPwd);
        User user2 = userService.login(userName, userPwd);
        if(user!=null){
            session.setAttribute("user",user2);
            return R.ok("登录成功").put("id",user2.getId());
        }
        return R.error("登录失败");
    }

//    @RequestMapping("/loginjsp")
//    public String loginjsp(){
//        return "userlogin";
//    }
}
