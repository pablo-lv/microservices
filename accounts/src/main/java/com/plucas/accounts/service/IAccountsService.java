package com.plucas.accounts.service;

import com.plucas.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber
     * @return CustomerDTO Object
     */
    CustomerDTO fetchAccountDetails(String mobileNumber);

    /**
     * @param customerDTO - CustomerDTO Object
     * @return boolean
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber
     * @return boolean
     */
    boolean deleteAccount(String mobileNumber);

    /**
     * @param accountNumber
     * @return boolean indicating if the communication status is updated or not
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
