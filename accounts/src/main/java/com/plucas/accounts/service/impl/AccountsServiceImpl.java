package com.plucas.accounts.service.impl;

import com.plucas.accounts.constants.AccountsConstants;
import com.plucas.accounts.dto.CustomerDTO;
import com.plucas.accounts.entity.Accounts;
import com.plucas.accounts.entity.Customer;
import com.plucas.accounts.exception.CustomerAlreadyExistsException;
import com.plucas.accounts.mapper.CustomerMapper;
import com.plucas.accounts.repository.AccountsRepository;
import com.plucas.accounts.repository.CustomerRepository;
import com.plucas.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> customerByMobileNumber = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (customerByMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException(AccountsConstants.CUSTOMER_ALREADY_EXISTS + customerDTO.getMobileNumber());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("ADMIN");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts accounts = accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("ADMIN");
        return newAccount;
    }
}
