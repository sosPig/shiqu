package com.sq.fs.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/19.
 */
@Entity
@Table(name="t_admin")
public class Admin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="a_name")
    private String name;

    @Column(name="a_job_num")
    private String jobNum;
    @Column(name="a_password")
    private String password;


    @Column(name="a_position")
    private String position;
    @Column(name="a_section")
    private String section;
    @Column(name="a_date")
    private String date;

//    @Column(name="a_photo_path")
//    private String photoPath;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getPhotoPath() {
//        return photoPath;
//    }
//
//    public void setPhotoPath(String photoPath) {
//        this.photoPath = photoPath;
//    }


}
