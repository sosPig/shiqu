package com.sq.fs.dao.impl;

import com.sq.fs.dao.VipDao;
import com.sq.fs.pojo.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/22.
 */
@Repository
public class VipDaoImpl extends BaseDaoImpl<Vip> implements VipDao  {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="Vip";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="idNum";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType = "asc";
    }
}
