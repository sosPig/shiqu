package com.sq.fs.dao;


import com.sq.fs.pojo.Admin;

/**
 * Created by Administrator on 2018/6/18.
 */
public interface AdminDao extends BaseDao<Admin> {
    void updatePassWord(Integer id,String password);
    void update1(Admin admin);
}
