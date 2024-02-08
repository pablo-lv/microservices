package com.plucas.accounts.controller;

import com.plucas.accounts.dto.CustomerDetailsDTO;
import com.plucas.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "CRUD Rest APIs for fetch Customer details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(
            @RequestHeader("banknet-correlation-id") String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber should 10 digits")
            String mobileNumber) {
        logger.debug("banknet-correlation-id: {}", correlationId);
        logger.debug("fetchCustomerDetails method started");
        CustomerDetailsDTO customerDetailsDTO = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
        logger.debug("fetchCustomerDetails method ended");
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDTO);
    }
}
