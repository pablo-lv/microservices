package com.plucas.accounts.service;

import com.plucas.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);
}
