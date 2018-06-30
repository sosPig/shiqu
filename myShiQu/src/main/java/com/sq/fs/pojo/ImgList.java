package com.sq.fs.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/26.
 */
@Entity
@Table(name="t_img_list")
public class ImgList  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="i_photo_path")
    private String photoPath;


    @Column(name="i_product_id")
    private Integer productId;


    public ImgList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
