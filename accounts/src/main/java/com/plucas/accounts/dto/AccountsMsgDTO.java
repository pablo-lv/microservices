package com.plucas.accounts.dto;

public record AccountsMsgDTO (
        Long accountNumber,
        String name,
        String email,
        String mobileNumber
){
}
