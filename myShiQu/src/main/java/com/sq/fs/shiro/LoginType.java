package com.sq.fs.shiro;

/**
 * Created by Administrator on 2018/7/11.
 */
public enum LoginType {
    USER("User"),  ADMIN("Admin");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
