package com.sq.fs.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/14.
 */
@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="u_name")
    private String name;

    @Column(name="u_job_num")
    private String jobNum;
    @Column(name="u_password")
    private String password;

    @Column(name="u_id_num")
    private String idNum;
    @Column(name="u_mail")
    private String mail;
    @Column(name="u_position")
    private String position;
    @Column(name="u_section")
    private String section;
    @Column(name="u_date")
    private String date;
    @Column(name="u_money")
    private String money;
    @Column(name="u_check_work")
    private String checkWork;
    @Column(name="u_remarks")
    private String remarks;
    @Column(name="u_photo_path")
    private String photoPath;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobNum='" + jobNum + '\'' +
                ", idNum='" + idNum + '\'' +
                ", mail='" + mail + '\'' +
                ", position='" + position + '\'' +
                ", section='" + section + '\'' +
                ", date='" + date + '\'' +
                ", money='" + money + '\'' +
                ", checkWork='" + checkWork + '\'' +
                ", remarks='" + remarks + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCheckWork() {
        return checkWork;
    }

    public void setCheckWork(String checkWork) {
        this.checkWork = checkWork;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
