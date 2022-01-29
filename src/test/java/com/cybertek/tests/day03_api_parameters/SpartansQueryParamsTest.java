package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import com.sun.org.apache.xpath.internal.operations.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartansQueryParamsTest {

    @BeforeAll //Run once before all tests
    public static void setUp(){
        System.out.println("Set up method");
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.url");  // http://44.201.134.164:1000/ords/hr

    }

    /**
     * Given accept type is Json
     *         And query parameter values are:
     *         gender|Male
     *         nameContains|kr
     *         When user sends GET request to /api/spartans/search
     *         Then response status code should be 200
     *         And response content-type: application/json
     *         And "Female" should be in response body
     *         And "Janette" should be in response body
     */


@Test
    public void searchForSpartanQueryTest(){
    Response response = given().accept(ContentType.JSON)
          //  .queryParams("gender", "Male", "nameContains", "kr")
            .and().queryParam("gender", "Male")
            .and().queryParam("nameContains", "kr")
            .when().get("/api/spartans/search");

    System.out.println("Status code = " + response.statusCode());
    assertEquals(200, response.statusCode());

    System.out.println("Containt Type" + response.contentType()); // read Content Type response header

    assertEquals("application/json", response.contentType());

    System.out.println(response.asString());
    assertTrue(response.asString().contains("Male"));
    assertTrue(response.asString().contains("krigora"));



}
}