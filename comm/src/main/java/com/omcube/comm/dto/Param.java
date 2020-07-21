package com.omcube.comm.dto;

import lombok.Data;

/**
 * 请求入参
 * @param <T>
 */
@Data
public class Param<T> {

    private Integer pageSize;

    private Integer page;

    private T data;


}
