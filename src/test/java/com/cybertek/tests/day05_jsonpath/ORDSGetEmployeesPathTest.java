package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetEmployeesPathTest extends ORDSTestBase{

    @Test
    public void getAllITProgrammersTest() {

        //IP/ords/hr/employees? q={"job_id":"IT_PROG"}
        //query parameter with HashMap
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("q" , "{\"job_id\":\"IT_PROG\"}" );

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramMap)
                //.and().queryParam("q" , "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        System.out.println("Status code = " + response.statusCode());
        response.prettyPrint();

        //print first employees emp id, first name, last name, email

        System.out.println("First emp id = " + response.path("items[0].employee_id"));
        System.out.println("First emp id = " + response.path("items[0].first_name"));
        System.out.println("First emp id = " + response.path("items[0].last_name"));
        System.out.println("First emp id = " + response.path("items[0].email"));

//you want to email all IT_PROGs, save all emails into List of String
        List<String> allEmails = response.path("items.email"); // all emails
        System.out.println("count = " + allEmails.size());
        System.out.println("Emails = " + allEmails);


        //you want to text all IT_PROGs save all phone into List of String
        List<String> allPhone = response.path("items.phone_number");
        System.out.println("AllPhones = " + allPhone);

        //verify that 590.423.4568 is among phone numbers
        assertTrue(allPhone.contains("590.423.4568"));
    }

}


