package com.phonebook.restAssuredTests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase {
    AuthRequestDto auth = AuthRequestDto.builder()
            .username("mail@ap-d.com")
            .password("Manuel1234$")
            .build();

    @Test
    public void loginSuccessTest() {
        AuthResponseDto dto = given()
                .contentType("application/json")
                .body(auth)
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(dto);
    }

    @Test
    public void loginSuccessTest2() {
        String responseToken = given()
                .contentType("application/json")
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .extract().path("token");
        System.out.println(responseToken);
    }
    @Test
    public void loginWithWrongEmailTest_401(){
        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("mail@ap-dcom")
                        .password("Manuel1234$")
                        .build())
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);
        Assert.assertEquals(errorDto.getError(),"Unauthorized");
    }
    @Test
    public void loginWrongPasswordTest_401(){
        given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("mail@ap-d.com")
                        .password("Manuel1234")
                        .build())
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Login or Password incorrect"));
    }
}