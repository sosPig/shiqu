package com.sq.fs.service;

import com.sq.fs.pojo.ImgList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */
public interface ImgListService {
    List<ImgList> queryList(Integer id);

    Serializable save(ImgList imglist);
    void deleteBatchs(Integer[] ids);
}
