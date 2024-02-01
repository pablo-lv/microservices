package com.plucas.accounts.service;

import com.plucas.accounts.dto.CustomerDetailsDTO;

public interface ICustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);
}
