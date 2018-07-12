package com.sq.fs.dao;


import com.sq.fs.pojo.User;

/**
 * Created by Administrator on 2018/6/18.
 */
public interface UserDao extends BaseDao<User> {
    void updatePassWord(Integer id,String password);
    void update1(User user);
}
