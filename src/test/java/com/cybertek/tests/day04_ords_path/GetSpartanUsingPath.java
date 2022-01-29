package com.cybertek.tests.day04_ords_path;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetSpartanUsingPath extends SpartanTestBase {

    /**
     * Given accept is json
     * And path param id is 440
     * When I send get request to /api/spartans/{id}
     * -----------------------------------
     * Then status code is 200
     * And content type is json
     * And id value is 440
     * And name is "krigora"
     * And gender is "Male"
     * And phone is "1231231234"
     */

    @Test
    public void readJsonWithPathTest() {
        //REQUEST

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 440)
                .when().get("/api/spartans/{id}");
//RESPONSE validations
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());
//Print value from JSON
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        //store value from JSON to variable
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        int phoneNum = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phoneNum = " + phoneNum);

        //assertions
        assertEquals(440, id);
        assertEquals("krigora", name);
        assertEquals("Male", gender);
        assertEquals(1231231234, phoneNum);
        assertEquals("1231231234", response.path("phone").toString());


      //  response.prettyPrint();
      //  assertTrue(response.asString().contains("440"));
     //   assertTrue(response.asString().contains("krigora"));
     //   assertTrue(response.asString().contains("Male"));
      //  assertTrue(response.asString().contains("1231231234"));

    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @Test
    public void readJsonArrayTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id"));  // All ids
        System.out.println("Id of first spartan = " + response.path("[0].id"));

        System.out.println("name of secont person = " + response.path("[1].name"));
//Print third person
        System.out.println("THIRD PERSON INFO");
        System.out.println(response.path("[2].id").toString());
        System.out.println(response.path("[2].name").toString());
        System.out.println(response.path("[2].gender").toString());
        System.out.println(response.path("[2].phone").toString());
        System.out.println("========================================");
        System.out.println(response.path("id[2]").toString());
        System.out.println(response.path("phone[2]").toString());

        System.out.println(response.path("[2]").toString());

        //print lasr person info
        System.out.println("LAST PERSON INFO");
        System.out.println(response.path("id[-1]").toString());
        System.out.println(response.path("name[-1]").toString());
        System.out.println(response.path("gender[-1]").toString());
        System.out.println(response.path("phone[-1]").toString());
        System.out.println("========================================");


        //store all first name into a list
        List<String> allName = response.path("name");
        System.out.println("name count = " + allName.size());

        for (String eachName : allName){
            System.out.println(eachName);
        }

        System.out.println("========================================");
        System.out.println(allName.contains("krigora"));


    }

}
