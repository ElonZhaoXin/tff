package com.tff.account.controller;

import com.tff.account.dto.AccountDto;
import com.tff.account.dto.GenericAccountResponse;
import com.tff.account.service.AccountService;
import com.tff.common.validation.PhoneNumber;
import io.swagger.annotations.Api;
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
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping(path = "/get_account_by_phonenumber")
    public GenericAccountResponse getAccountByPhoneNumber(@RequestParam @PhoneNumber String phoneNumber) {
        AccountDto accountDto = accountService.getAccountBy(phoneNumber);
        return new GenericAccountResponse(accountDto);
    }
}
