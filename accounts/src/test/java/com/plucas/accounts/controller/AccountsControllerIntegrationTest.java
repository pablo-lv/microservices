package com.plucas.accounts.controller;

import com.netflix.discovery.converters.Auto;
import com.plucas.accounts.constants.AccountsConstants;
import com.plucas.accounts.dto.CustomerDTO;
import com.plucas.accounts.dto.ResponseDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"build.version=3.0", "eureka.client.enabled=false"})
@TestPropertySource(locations = "/application.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountsControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetJavaVersion() {
        //arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(null, headers);
        //act
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/java-version", String.class);
        String responseBody = response.getBody();
        //assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetBuildInfo() {
        //arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(null, headers);
        //act
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/build-info", String.class);
        String responseBody = response.getBody();
        //assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(responseBody.contains("3.0"));
    }

    @Test
    public void testCreateAccount_whenValidDetailsProvided_returnsAccountDetails() throws JSONException {
        //arrange
        JSONObject accountDetailsRequestJson = new JSONObject();
        accountDetailsRequestJson.put("name", "plucas");
        accountDetailsRequestJson.put("email", "plucas@test.com");
        accountDetailsRequestJson.put("mobileNumber", "4354437687");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(accountDetailsRequestJson.toString(), headers);

        //act
        ResponseEntity<ResponseDTO> response = testRestTemplate.postForEntity("/api/create", request, ResponseDTO.class);
        ResponseDTO createdAccountDetails = response.getBody();
        LinkedHashMap<String, String> createdAccount = (LinkedHashMap<String, String>) createdAccountDetails.getData();


        //assert
        Assertions.assertEquals(AccountsConstants.STATUS_201, createdAccountDetails.getStatusCode());
        Assertions.assertEquals(accountDetailsRequestJson.getString("name"),
                createdAccount.get("name") , "Returned account's name is not the same");


    }
}
