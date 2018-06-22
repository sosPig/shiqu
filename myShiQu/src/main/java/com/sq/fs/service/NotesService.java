package com.sq.fs.service;

import com.sq.fs.pojo.Notes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */
public interface NotesService {
    List<Notes> queryList();

    Notes queryById(Integer id);

    Serializable save(Notes notes);

    void update(Integer id, Notes notes);

    void deleteBatch(Integer[] ids);

}
