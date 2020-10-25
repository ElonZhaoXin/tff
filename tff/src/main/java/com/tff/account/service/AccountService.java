package com.tff.account.service;

import com.tff.account.dto.AccountDto;

public interface AccountService {
    AccountDto getAccountBy(String phoneNumber);
}
