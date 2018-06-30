package com.sq.fs.service;

import com.sq.fs.pojo.Admin;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
public interface AdminService {
    List<Admin> queryList();

    Admin queryById(Integer id);

    Serializable save(Admin admin);

    void update(Integer id, Admin admin);

    void deleteBatch(Integer[] ids);
    Admin login(String adminName, String adminPwd);
    void updatePassWord(Integer id,String password);
}
