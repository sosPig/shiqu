package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.Notes;
import com.sq.fs.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */
@Controller
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/add")
    public R add( Notes notes){
        notesService.save(notes);
        return R.ok("保存成功");
    }

    @ResponseBody
    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Notes notes = notesService.queryById(id);

        return R.ok().put("data",notes);
    }

    @ResponseBody
    @RequestMapping("/show")
    public R show(){

        List<Notes> notesList = notesService.queryList();
        return R.ok().put("data",notesList);
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

       // System.out.println(paramters);
        Integer[] ids = paramters.get("id");
        notesService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @ResponseBody
    @RequestMapping("/mod/{id}")
    public R update(@RequestBody Notes notes,@PathVariable Integer id){

        System.out.println(notes.toString());
        notesService.update(id, notes);
        return R.ok("更新成功");
    }
}
