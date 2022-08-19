package com.example.demo.application.services.interfaces;

import com.example.demo.application.dto.in.AccountDto;

public interface IAccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateAccount(AccountDto accountDto);

    Boolean deleteAccount(String numberAccount);
}
