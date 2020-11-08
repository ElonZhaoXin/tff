package com.tff.account.service.impl;

import com.tff.account.entity.Account;
import com.tff.account.mapper.AccountMapper;
import com.tff.account.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tff
 * @since 2020-11-08
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
