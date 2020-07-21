package com.omcube.comm.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页返回消息
 * @param <T>
 */
@Data
public class Page<T> {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页
     */
    private Long current;


    /**
     * 查询结果
     */
    private List<T> data;



}
