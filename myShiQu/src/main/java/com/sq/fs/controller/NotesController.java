package com.sq.fs.controller;

import com.sq.fs.dto.R;
import com.sq.fs.pojo.Notes;
import com.sq.fs.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */
@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/add")
    public R add(@RequestBody Notes notes){
        Serializable save = notesService.save(notes);
//        System.out.println(save);

        return R.ok("保存成功");
    }

    @RequestMapping("/read/{id}")
    public R read(@PathVariable Integer id){

        Notes notes = notesService.queryById(id);

        return R.ok().put("data",notes);
    }

    @RequestMapping("/show")
    public R show(){

        List<Notes> notesList = notesService.queryList();
        return R.ok().put("data",notesList);
    }


    @RequestMapping("/remove")
    public R del(@RequestBody Map<String, Integer[]> paramters){

       // System.out.println(paramters);
        Integer[] ids = paramters.get("idList");
        notesService.deleteBatch(ids);
        return R.ok("删除成功");
    }


    @RequestMapping("/mod/{id}")
    public R update(@RequestBody Notes notes,@PathVariable Integer id){

//        System.out.println(notes.toString());

        notesService.update(id, notes);
        return R.ok("更新成功");
    }
}
