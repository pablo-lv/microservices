package com.plucas.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold Error Response information")
public class ErrorResponseDTO {

    private String apiPath;
    @Schema(description = "Error Message", example = "Invalid Request")
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
