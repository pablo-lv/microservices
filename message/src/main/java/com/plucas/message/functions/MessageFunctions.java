package com.plucas.message.functions;

import com.plucas.message.dto.AccountsMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMsgDTO, AccountsMsgDTO> email() {
        return accountsMsgDTO -> {
            log.info("Emailing: " + accountsMsgDTO.toString());
            return accountsMsgDTO;
        };
    }

    @Bean
    public Function<AccountsMsgDTO, Long> sms() {
        return accountsMsgDTO -> {
            log.info("SMS: " + accountsMsgDTO.toString());
            return accountsMsgDTO.accountNumber();
        };
    }
}
