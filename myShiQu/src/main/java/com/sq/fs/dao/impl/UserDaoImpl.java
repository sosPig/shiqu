package com.sq.fs.dao.impl;

import com.sq.fs.dao.UserDao;
import com.sq.fs.pojo.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/18.
 */
@Repository
//@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao  {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="User";
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
        Query queryupdate=currentSession().createQuery("update "+className+" user set user.password= ? where user.id = ?");
        queryupdate.setParameter(0, password);
        queryupdate.setParameter(1, id);
        queryupdate.executeUpdate();

    }

    @Override
    public void update1( User user) {
        Query queryupdate=currentSession().createQuery("update "+className+" user set user.name= ?,user.jobNum = ?,user.mail =?,user.position = ?,user.section = ? ,user.date =? ,user.remarks =? where user.id = ?");
        queryupdate.setParameter(0, user.getName());
        queryupdate.setParameter(1, user.getJobNum());
        queryupdate.setParameter(2, user.getMail());
        queryupdate.setParameter(3, user.getPosition());
        queryupdate.setParameter(4, user.getSection());
        queryupdate.setParameter(5, user.getDate());

        queryupdate.setParameter(6, user.getRemarks());
        queryupdate.setParameter(7, user.getId());
        queryupdate.executeUpdate();
    }

    @Override
    public void updateRemark( User user) {
        Query queryupdate=currentSession().createQuery("update "+className+" user set user.money =? ,user.remarks =?  where user.id = ?");

        queryupdate.setParameter(0, user.getMoney());

        queryupdate.setParameter(1, user.getRemarks());

        queryupdate.setParameter(2, user.getId());
        queryupdate.executeUpdate();
    }
}
