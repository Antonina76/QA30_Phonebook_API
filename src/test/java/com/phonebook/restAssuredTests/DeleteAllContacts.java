package com.phonebook.restAssuredTests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.MessageDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllContacts  extends TestBase{
    AuthRequestDto auth1 = AuthRequestDto.builder()
            .username("mail@ap-d.com")
            .password("Manuel1234$")
            .build();



    @Test
    public void deleteAllContactsTest(){
                given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete("contacts/clear")
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void deleteAllContacts_401(){

        given()
                .contentType(ContentType.JSON)
                .header("Authorisation",token)
            //    .body(auth1)
                .when()
                .delete("contacts/clear")
                .then()
                .assertThat().statusCode(401);

    }


}
