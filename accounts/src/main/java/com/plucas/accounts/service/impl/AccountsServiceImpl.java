package com.plucas.accounts.service.impl;

import com.plucas.accounts.constants.AccountsConstants;
import com.plucas.accounts.dto.AccountsDTO;
import com.plucas.accounts.dto.CustomerDTO;
import com.plucas.accounts.entity.Accounts;
import com.plucas.accounts.entity.Customer;
import com.plucas.accounts.exception.CustomerAlreadyExistsException;
import com.plucas.accounts.exception.ResourceNotFoundException;
import com.plucas.accounts.mapper.AccountsMapper;
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
        return newAccount;
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->{
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        });

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString());
        });

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(accounts, new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDto) {
        boolean isUpdated = false;
        AccountsDTO accountsDto = customerDto.getAccountsDTO();
        if(accountsDto != null ) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
