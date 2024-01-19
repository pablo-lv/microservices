package com.plucas.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty(message = "AccountNumber cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber should 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress cannot be empty")
    private String branchAddress;
}
