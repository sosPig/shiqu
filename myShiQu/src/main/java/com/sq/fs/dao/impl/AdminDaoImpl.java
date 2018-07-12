package com.sq.fs.dao.impl;

import com.sq.fs.dao.AdminDao;
import com.sq.fs.pojo.Admin;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/18.
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="Admin";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="jobNum";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType = "asc";
    }

    @Override
    public void updatePassWord(Integer id,String password) {
        Query queryupdate=currentSession().createQuery("update "+className+" a set a.password= ? where a.id = ?");
        queryupdate.setParameter(0, password);
        queryupdate.setParameter(1, id);
        int ret=queryupdate.executeUpdate();

    }
    @Override
    public void update1( Admin admin) {
        Query queryupdate=currentSession().createQuery("update "+className+" admin set admin.name= ?,admin.jobNum = ?,admin.position = ?,admin.section = ? ,admin.date =? where admin.id = ?");
        queryupdate.setParameter(0, admin.getName());
        queryupdate.setParameter(1, admin.getJobNum());

        queryupdate.setParameter(2, admin.getPosition());
        queryupdate.setParameter(3, admin.getSection());
        queryupdate.setParameter(4, admin.getDate());

        queryupdate.setParameter(5, admin.getId());
        queryupdate.executeUpdate();
    }
}
