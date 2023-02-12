package com.capitole.techtest.restfulwebservices.controller;

import com.capitole.techtest.restfulwebservices.RestfulWebServicesApplication;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestfulWebServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @DisplayName("Test")
    @ParameterizedTest(name="{displayName} {index}")
    @CsvSource(delimiterString = ";", value = {
            "2020-06-14-10.00.00;35455;1;{\"price_list\":1,\"start_date\":\"2020-06-14-00.00.00\",\"end_date\":\"2020-12-31-23.59.59\",\"brand_id\":1,\"product_id\":35455,\"price\":35.50,\"currency\":\"EUR\"}",
            "2020-06-14-16.00.00;35455;1;{\"price_list\":2,\"start_date\":\"2020-06-14-15.00.00\",\"end_date\":\"2020-06-14-18.30.00\",\"brand_id\":1,\"product_id\":35455,\"price\":25.45,\"currency\":\"EUR\"}",
            "2020-06-14-21.00.00;35455;1;{\"price_list\":1,\"start_date\":\"2020-06-14-00.00.00\",\"end_date\":\"2020-12-31-23.59.59\",\"brand_id\":1,\"product_id\":35455,\"price\":35.50,\"currency\":\"EUR\"}",
            "2020-06-15-10.00.00;35455;1;{\"price_list\":3,\"start_date\":\"2020-06-15-00.00.00\",\"end_date\":\"2020-06-15-11.00.00\",\"brand_id\":1,\"product_id\":35455,\"price\":30.50,\"currency\":\"EUR\"}",
            "2020-06-16-21.00.00;35455;1;{\"price_list\":4,\"start_date\":\"2020-06-15-16.00.00\",\"end_date\":\"2020-12-31-23.59.59\",\"brand_id\":1,\"product_id\":35455,\"price\":38.95,\"currency\":\"EUR\"}"
    })
    public void test(String date, int productId, int brandId, String expected) throws JSONException {

        headers.add(HttpHeaders.ACCEPT,"application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/price?brandId=" + brandId + "&productId=" + productId + "&date=" + date),
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}