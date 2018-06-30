package com.sq.fs.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/22.
 */
@Entity
@Table(name="t_notes")
public class Notes  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="n_name")
    private String name;
    @Column(name="n_details")
    private String details;
    @Column(name="n_date")
    private String date;

    public Notes() {
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
