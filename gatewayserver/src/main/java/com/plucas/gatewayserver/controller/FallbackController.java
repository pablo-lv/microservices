package com.plucas.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @RequestMapping(value = "/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("Please contact support");
    }
}
