package com.sq.fs.service;

import com.sq.fs.pojo.Vip;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */

public interface VipService {
    List<Vip> queryList();

    Vip queryById(Integer id);

    Serializable save(Vip vip);

    void update(Integer id, Vip vip);

    void deleteBatch(Integer[] ids);
   
}
