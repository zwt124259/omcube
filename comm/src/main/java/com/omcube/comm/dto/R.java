package com.omcube.comm.dto;

import com.omcube.comm.constant.SystemConstant;
import lombok.Data;

/**
 * 消息返回
 * @param <T>
 */
@Data
public class R<T> {

    private String code;

    private String message;

    private T data;

    public R(){

    }

    public R(String code,String message){
        this.code=code;
        this.message=message;
    }

    public R success(T data){

       R r = new R(SystemConstant.BIZ_SUCCESS,SystemConstant.BIZ_MESSAGE);

       r.setData(data);

       return r;
    }


    public R fail(String message){

        R r = new R(SystemConstant.BIZ_FAIL,message);

        return r;
    }

}
