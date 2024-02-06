package com.plucas.accounts.service.impl;

import com.plucas.accounts.dto.AccountsDTO;
import com.plucas.accounts.dto.CardsDTO;
import com.plucas.accounts.dto.CustomerDetailsDTO;
import com.plucas.accounts.dto.LoansDTO;
import com.plucas.accounts.entity.Accounts;
import com.plucas.accounts.entity.Customer;
import com.plucas.accounts.exception.ResourceNotFoundException;
import com.plucas.accounts.mapper.AccountsMapper;
import com.plucas.accounts.mapper.CustomerMapper;
import com.plucas.accounts.repository.AccountsRepository;
import com.plucas.accounts.repository.CustomerRepository;
import com.plucas.accounts.service.ICustomerService;
import com.plucas.accounts.service.client.CardsFeignClient;
import com.plucas.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->{
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        });

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString());
        });

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(accounts, new AccountsDTO()));


        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (loansDTOResponseEntity != null) {
            customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());
        }

        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (cardsDTOResponseEntity != null) {
            customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());
        }

        return customerDetailsDTO;
    }
}
