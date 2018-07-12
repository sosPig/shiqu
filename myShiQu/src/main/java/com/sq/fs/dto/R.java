package com.sq.fs.dto;

import java.util.HashMap;

/**
 * Created by helen on 2018/6/12
 */
public class R extends HashMap<String, Object> {

    public R(){
        put("ret",true);
    }

    public static R ok(){
        R r = new R();
        r.put("ret", true);
        return r;
    }

    public static R ok(String msg){
        R r = new R();
        r.put("ret", true);
        r.put("msg", msg);
        return r;
    }

    public static R ok(boolean ret, String msg){
        R r = new R();
        r.put("ret", ret);
        r.put("msg", msg);
        return r;
    }

    public static R error(){
        R r = new R();
        r.put("ret", false);
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.put("ret", false);
        r.put("errmsg", msg);
        return r;
    }

    public static R error(boolean ret, String msg){
        R r = new R();
        r.put("ret", ret);
        r.put("errmsg", msg);
        return r;
    }

    public R put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
