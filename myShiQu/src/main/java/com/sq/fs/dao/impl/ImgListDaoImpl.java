package com.sq.fs.dao.impl;

import com.sq.fs.dao.ImgListDao;
import com.sq.fs.pojo.ImgList;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/6/26.
 */
@Repository
public class ImgListDaoImpl extends BaseDaoImpl<ImgList> implements ImgListDao
{
    @Override
    @Autowired
    protected void setClassName() {
        super.className="ImgList";
    }

    @Override
    @Autowired
    protected void setKeyName() {
        super.keyName="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderColum() {
        super.defaultOrderColum="id";
    }

    @Override
    @Autowired
    protected void setDefaultOrderType() {
        super.defaultOrderType="asc";
    }

    @Override
    public void deleteBatchs(Integer[] ids) {
        this.keyName="i_product_id";
        Query<ImgList> q = currentSession().createQuery(
                " delete from " + className + " where " + keyName + " in (:ids)");
        q.setParameterList("ids", ids);
        q.executeUpdate();
    }
}
