package com.sq.fs.service.impl;

import com.sq.fs.dao.VipDao;
import com.sq.fs.pojo.Vip;
import com.sq.fs.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipDao vipDao;
    @Override
    public List<Vip> queryList() {
        List<Vip> vipList = vipDao.findAll();
        return vipList;
    }

    @Override
    public Vip queryById(Integer id) {
        Vip vip = vipDao.getById(id);
        return vip;
    }

    @Override
    public Serializable save(Vip vip) {
        Serializable serializable = vipDao.save(vip);
        return serializable;
    }

    @Override
    public void update(Integer id, Vip vip) {
        vip.setId(id);
        vipDao.update(vip);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        vipDao.deleteBatch(ids);
    }
}
