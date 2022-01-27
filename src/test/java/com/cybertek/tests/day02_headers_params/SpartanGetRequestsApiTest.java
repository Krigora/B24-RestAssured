package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartanGetRequestsApiTest {
    String baseUrl = ConfigurationReader.getProperty("spartan.url");

    @Test
    public void getAllSpartansTest() {
        Response response = when().get(baseUrl + "/api/spartans");
        System.out.println("Status done = " + response.statusCode());
        assertEquals(200, response.statusCode());

        response.prettyPrint();
        assertTrue(response.asString().contains("Correy"));

    }

    /**
     * Given Accept type is "application/json"
     * When I send a GET request to
     * spartan_base_url/api/spartans
     * Then Response STATUS CODE must be 200
     * And content type should be "application/json"
     */

    @Test
    public void allSpartansHeadersTest() {
       Response response = given().accept(ContentType.JSON).
                           when().get(baseUrl + "/api/spartans");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println(response.contentType()); // read Content Type response header

        assertEquals("application/json", response.contentType());

        //print more response header
        System.out.println("date header value = " + response.getHeader("Date") );

        System.out.println("Transfer encoding = " + response.getHeader("Transfer-Encoding") );
        //verify header "DAte" is present
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));
    }
}
