package com.sq.fs.service.impl;

import com.sq.fs.dao.UserDao;
import com.sq.fs.pojo.User;
import com.sq.fs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<User> queryList() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    @Override
    public User queryById(Integer id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public Serializable save(User user) {
        Serializable serializable = userDao.save(user);
        return serializable;
    }

    @Override
    public void update(Integer id, User user) {
        user.setId(id);
        userDao.update(user);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        userDao.deleteBatch(ids);
    }

    @Override
    public User login(String userName,String userPwd) {
        List list=new ArrayList();
        list.add(userName);
        list.add(userPwd);
        User user = userDao.get("from User where u_job_num = ? and u_password = ? ",list);
        return user;


    }
}
