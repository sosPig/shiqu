package com.sq.fs.service.impl;

import com.sq.fs.dao.AdminDao;
import com.sq.fs.pojo.Admin;
import com.sq.fs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Admin> queryList() {
        List<Admin> adminList = adminDao.findAll();
        return adminList;
    }

    @Override
    public void updatePassWord(Integer id,String password) {
        adminDao.updatePassWord(id,password);
    }
    @Override
    public Admin queryById(Integer id) {
        Admin admin = adminDao.getById(id);
        return admin;
    }

    @Override
    public Serializable save(Admin admin) {
        Serializable serializable = adminDao.save(admin);
        return serializable;
    }

    @Override
    public void update(Integer id, Admin admin) {
        admin.setId(id);
        adminDao.update(admin);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        adminDao.deleteBatch(ids);
    }

    @Override
    public Admin login(String adminName,String adminPwd) {
        List list=new ArrayList();
        list.add(adminName);
        list.add(adminPwd);
        Admin admin = adminDao.get("from Admin where u_job_num = ? and u_password = ? ",list);
        return admin;


    }
}
