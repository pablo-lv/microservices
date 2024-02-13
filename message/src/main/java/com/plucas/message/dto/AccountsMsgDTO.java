package com.plucas.message.dto;

/**
 * DTO for Accounts message
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */
public record AccountsMsgDTO (Long accountNumber, String name, String email, String mobileNumber) {
}
