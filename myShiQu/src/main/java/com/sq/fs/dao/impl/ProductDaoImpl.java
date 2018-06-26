package com.sq.fs.dao.impl;

import com.sq.fs.dao.ProductDao;
import com.sq.fs.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/26.
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    @Override
    @Autowired
    protected void setClassName() {
        super.className="Product";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="idNum";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType="asc";
    }
}
