package com.tff.account.service.impl;

import com.tff.account.dto.AccountDto;
import com.tff.account.model.Account;
import com.tff.account.repo.AccountRepo;
import com.tff.account.service.AccountService;
import com.tff.common.api.ResultCode;
import com.tff.common.exception.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AccountDto getAccountBy(String phoneNumber) {
        //Account account = accountRepo.findAccountByPhoneNumber(phoneNumber);
        Account account = accountRepo.findAccountByPhoneNumberCustom(phoneNumber);
        if (ObjectUtils.isEmpty(account)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "User with specified phonenumber not found");
        }
        return modelMapper.map(account, AccountDto.class);
    }
}
