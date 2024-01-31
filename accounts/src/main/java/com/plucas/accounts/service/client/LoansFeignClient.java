package com.plucas.accounts.service.client;

import com.plucas.accounts.dto.CardsDTO;
import com.plucas.accounts.dto.LoansDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="loans")
public interface LoansFeignClient {

    @GetMapping(value ="/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam String mobileNumber);
}
