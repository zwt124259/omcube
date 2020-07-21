package com.omcube.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.omcube.biz.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao extends BaseMapper<AccountEntity> {
}
