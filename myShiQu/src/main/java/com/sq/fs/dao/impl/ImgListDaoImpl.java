package com.sq.fs.dao.impl;

import com.sq.fs.dao.ImgListDao;
import com.sq.fs.pojo.ImgList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/26.
 */
@Repository
public class ImgListDaoImpl extends BaseDaoImpl<ImgList> implements ImgListDao {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="ImgList";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType="asc";
    }
}
