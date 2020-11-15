package com.tff.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tff.account.dto.AccountDto;
import com.tff.account.dto.GenericAccountResponse;
import com.tff.account.entity.Account;
import com.tff.account.service.IAccountService;
import com.tff.common.validation.PhoneNumber;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see com.tff.common.conf.SwaggerConfig Api doc
 */
@Api(value = "账户服务")
@RestController
@Validated
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;
    private final  ModelMapper modelMapper;

    @GetMapping(path = "/get_account_by_phonenumber")
    public GenericAccountResponse getAccountByPhoneNumber(@RequestParam @PhoneNumber String phoneNumber) {
        Account one = accountService.getOne(new QueryWrapper<Account>().lambda().eq(Account::getPhoneNumber, phoneNumber));
        return new GenericAccountResponse(modelMapper.map(one, AccountDto.class));
    }
}
