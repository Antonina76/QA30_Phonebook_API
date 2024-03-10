package com.phonebook.restAssuredTests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;
import com.phonebook.dto.MessageDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllContacts  extends TestBase{
    AuthRequestDto auth1 = AuthRequestDto.builder()
            .username("mail@ap-d.com")
            .password("Manuel1234")
            .build();
    @BeforeMethod
    public void precondition(){
        ContactDto contactDto = ContactDto.builder()
                .name("Oliver")
                .lastName("Kan")
                .phone("1234567890")
                .email("kan@gm.com")
                .address("Berlin")
                .description("goalkeeper")
                .build();

            given()
                .contentType(ContentType.JSON)
               .header("Authorization", token)
               .body(contactDto)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(containsString("Contact was added!"));

    }


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
