package com.sq.fs.service;

import com.sq.fs.pojo.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */

public interface UserService {
    List<User> queryList();

    User queryById(Integer id);

    Serializable save(User user);

    void update(Integer id, User user);

    void deleteBatch(Integer[] ids);
    User login(String userName);
    void updatePassWord(Integer id,String password);
    void updateRemark(User user);
}
