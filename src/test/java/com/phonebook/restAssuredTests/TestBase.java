package com.phonebook.restAssuredTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFpbEBhcC1kLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzA5OTIxMjA3LCJpYXQiOjE3MDkzMjEyMDd9.HhqTMZrUKyqLCz3nOE03nIly8Zco9hyfKbY4RDk8_FA";


    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}
