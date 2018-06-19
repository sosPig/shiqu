package com.sq.fs.dao.impl;

import com.sq.fs.dao.UserDao;
import com.sq.fs.pojo.User;
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
}
