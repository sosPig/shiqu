package com.sq.fs.dao;

import com.sq.fs.pojo.ImgList;

/**
 * Created by Administrator on 2018/6/26.
 */
public interface ImgListDao extends BaseDao<ImgList> {
    void deleteBatchs(Integer[] ids);
}
