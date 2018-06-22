package com.sq.fs.dao.impl;

import com.sq.fs.dao.NotesDao;
import com.sq.fs.pojo.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/22.
 */
@Repository
public class NotesDaoImpl extends BaseDaoImpl<Notes> implements NotesDao {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="Motes";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="date";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType="desc";
    }
}
