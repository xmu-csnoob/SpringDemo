package com.example.staff.utils;

import lombok.Data;

@Data
public class ReturnObject<T> {
    Long errno;
    T data;
    String errmsg;
    public ReturnObject(){
        errno=0L;
        data=null;
        errmsg="success";
    }
    public ReturnObject(T data){
        errno=0L;
        this.data=data;
        errmsg="success";
    }
    public ReturnObject(Long errno,String errmsg){
        this.errno=errno;
        this.data=null;
        this.errmsg=errmsg;
    }
}
