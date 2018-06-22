package com.sq.fs.service.impl;

import com.sq.fs.dao.NotesDao;
import com.sq.fs.pojo.Notes;
import com.sq.fs.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */
@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesDao notesDao;
    @Override
    public List<Notes> queryList() {
        List<Notes> notesList = notesDao.findAll();
        return notesList;
    }

    @Override
    public Notes queryById(Integer id) {
        Notes notes = notesDao.getById(id);
        return notes;
    }

    @Override
    public Serializable save(Notes notes) {
        Serializable serializable = notesDao.save(notes);
        return serializable;
    }

    @Override
    public void update(Integer id, Notes notes) {
        notes.setId(id);
        notesDao.update(notes);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        notesDao.deleteBatch(ids);
    }
}
