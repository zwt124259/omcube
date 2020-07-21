package com.omcube.biz.service.account.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.omcube.biz.dao.AccountDao;
import com.omcube.biz.entity.AccountEntity;
import com.omcube.biz.service.account.AccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {
}
