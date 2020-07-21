package com.omcube.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account")
public class AccountEntity {

    private String accountName;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;


}
