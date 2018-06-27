package com.sq.fs.service.impl;

import com.sq.fs.dao.ImgListDao;
import com.sq.fs.pojo.ImgList;
import com.sq.fs.service.ImgListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
@Service
public class ImgListServiceImpl implements ImgListService {

    @Autowired
    private ImgListDao imgListDao;

    @Override
    public List<ImgList> queryList(Integer id) {
        List list=new ArrayList();
        list.add(id);
        List<ImgList> imglist = imgListDao.find("FROM ImgList WHERE i_product_id = ?", list);
        return imglist;
    }
}
