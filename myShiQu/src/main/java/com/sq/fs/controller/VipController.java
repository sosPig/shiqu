package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.Vip;
import com.sq.fs.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */
@Controller
@RequestMapping("/api/vip")
public class VipController {

    @Autowired
    private VipService vipService;
    @ResponseBody
    @RequestMapping("/add")
    public R add(@RequestBody Vip vip){
        Serializable save = vipService.save(vip);
        System.out.println(save);
        return R.ok("保存成功");
    }

    @ResponseBody
    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Vip vip = vipService.queryById(id);

        return R.ok().put("data",vip);
    }

    @ResponseBody
    @RequestMapping("/show")
    public R show(){

        List<Vip> vipList = vipService.queryList();
        return R.ok().put("data",vipList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

       // System.out.println(paramters);
        Integer[] ids = paramters.get("id");
        vipService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@RequestBody Vip vip,@PathVariable("id") Integer id){

        System.out.println(vip.toString());
        vipService.update(id, vip);
        return R.ok("更新成功");
    }
}
