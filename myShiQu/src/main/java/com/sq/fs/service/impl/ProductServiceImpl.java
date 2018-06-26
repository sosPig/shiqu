package com.sq.fs.service.impl;

import com.sq.fs.dao.ProductDao;
import com.sq.fs.pojo.Product;
import com.sq.fs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
//    @Autowired
//    private ImgListDao imgListDao;
    @Override
    public List<Product> queryList() {
        List<Product> productList = productDao.findAll();
        return productList;
    }

    @Override
    public Product queryById(Integer id) {
        Product product = productDao.getById(id);
        return product;
    }

    @Override
    public Serializable save(Product product) {
        Serializable serializable = productDao.save(product);
        return serializable;
    }

    @Override
    public void update(Integer id, Product product) {
        product.setId(id);
        productDao.update(product);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        productDao.deleteBatch(ids);
    }
}
