package com.plucas.accounts.service.client;

import com.plucas.accounts.dto.LoansDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient {
    @Override
    public ResponseEntity<LoansDTO> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
