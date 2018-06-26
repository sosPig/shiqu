package com.sq.fs.service;

import com.sq.fs.pojo.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
public interface ProductService {
    List<Product> queryList();

    Product queryById(Integer id);

    Serializable save(Product product);

    void update(Integer id, Product product);

    void deleteBatch(Integer[] ids);
}
