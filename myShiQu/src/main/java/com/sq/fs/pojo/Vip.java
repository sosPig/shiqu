package com.sq.fs.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/22.
 */
@Entity
@Table(name="t_vip")
public class Vip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="v_name")
    private String name;
    @Column(name="v_id_num")
    private String idNum;
    @Column(name="v_mail")
    private String mail;
    @Column(name="v_date")
    private String date;
    @Column(name="v_phone_num")
    private String phoneNum;
    @Column(name="v_birthday")
    private String birthday;
    @Column(name="v_record")
    private String record;
    @Column(name="v_integral")
    private String integral;

    public Vip() {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNum='" + idNum + '\'' +
                ", mail='" + mail + '\'' +
                ", date='" + date + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", birthday='" + birthday + '\'' +
                ", record='" + record + '\'' +
                ", integral='" + integral + '\'' +
                '}';
    }
}
