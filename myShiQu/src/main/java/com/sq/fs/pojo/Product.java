package com.sq.fs.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 *
 "id":1,//id
 "name":"西红柿炒鸡蛋", // 产品名称：[字符串：必填]
 "idNum":"201803201132", // 产品id:[整型：必填]
 "imglist":{
 img:src,
 img:src,
 img:src,
 ...
 }, // 产品图片:[字符串:必填]
 "price":"100.00", // 原价:[字符串:必填]
 "vipPrice":"85.00", // 会员价:[字符串:必填]
 "type":"中餐"//类型:[字符串：必填]
 */
@Entity
@Table(name="t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="p_name")
    private String name;

    @Column(name="p_id_num")
    private String idNum;
    @Column(name="p_price")
    private String price;


    @Column(name="p_vip_price")
    private String vipPrice;
    @Column(name="p_type")
    private String type;

    @OneToMany(mappedBy = "product",fetch=FetchType.EAGER )
    @JoinColumn(name = "pid")
    private List<ImgList> imglist =new ArrayList<ImgList>() ;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ImgList> getImglist() {
        return imglist;
    }

    public void setImglist(List<ImgList> imglist) {
        this.imglist = imglist;
    }


}
