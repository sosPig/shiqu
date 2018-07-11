package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.User;
import com.sq.fs.service.UserService;
import com.sq.fs.shiro.CustomizedToken;
import com.sq.fs.shiro.LoginType;
import com.sq.fs.utils.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;




/**
 * Created by Administrator on 2018/6/18.
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public R add( User user,
                 @RequestParam("file1") MultipartFile file,
                 Model model){


        if(file!=null&&file.getOriginalFilename()!=null&&file.getOriginalFilename().length()>0){

            String oldFilename = file.getOriginalFilename();


            String dex = oldFilename.substring(oldFilename.indexOf(".")+1);
            List<String> dexs=new ArrayList<>();
            dexs.add("jpg");
            dexs.add("png");
            if (!dexs.contains(dex)) {
//                System.out.println("不支持的文件格式");
                return R.error("不支持的文件格式");
            }
            String newFilename= UUID.randomUUID().toString()+oldFilename.substring(oldFilename.lastIndexOf("."));
            String fileAdress="D:\\shiqu\\picture\\";
            File file2=new File(fileAdress);
            if(!file2.exists()){
                file2.mkdirs();
            }
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
    public R show(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        List<User> userList = userService.queryList();
        return R.ok().put("data",userList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
//        System.out.println(paramters);
        Integer[] ids = paramters.get("idList");
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
    public R login(@RequestBody User user){

        String username=user.getJobNum();
        String password=user.getPassword();
//        Boolean rememberMe=false;
//        if("true".equals(rememberMeString)){
//            rememberMe=true;
//        }



        Subject subject = ShiroUtils.getSubject();
        CustomizedToken customizedToken = new CustomizedToken(username, password, USER_LOGIN_TYPE);
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(customizedToken);
        User user1 = userService.login(username, password);
        return R.ok().put("data",user1);
    }


}
