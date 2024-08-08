package com.fiap.hackathon.integration;

import com.fiap.hackathon.dto.DadosAutenticacao;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

    @Test
    public void test(){
        var json = "{\n" +
                "\t\"login\":\"matheus\",\n" +
                "\t\"senha\":\"123456\"\n" +
                "}";
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(json)
                .when()
                    .post("/login");

        String token = response.getBody().jsonPath().getString("token");
        System.out.println(token);
    }

}
