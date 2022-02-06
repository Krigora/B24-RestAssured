package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPostRequestTest extends SpartanTestBase {
    /**
     * given accept is json
     * and content type is json
     * when I send POST request to /api/spartans
     * with {
     * "gender": "Male",
     * "name": "Teacher Request",
     * "phone": 2112562345
     * }
     * Then status code is 201
     * And "spartan is born" message should be displayed
     */

    @Test
    public void postSpartanTest() {

        String requestJson = " {\"gender\": \"Male\",\n" +
                " \"name\": \"Ihor RestAssur\",\n" +
                "\"phone\": 9874563212}";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestJson)
                .when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();
        //verify status code
        assertThat(response.statusCode(), is(201));
        assertEquals(201, response.statusCode());  //jUnit

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //verify json response body
        // And "spartan is born" message should be displayed

        System.out.println("messeage = " + response.path("success"));
        assertThat(response.path("success"), is(equalTo("A Spartan is Born!")));


    }

    @Test
    public void postSpartanWitMapTest() {
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name", "MyTestMapPost");
        requestMap.put("gender", "Female");
        requestMap.put("phone", 1237418520L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("/api/spartans");
        System.out.println("Status code = " + response.statusCode());
        /**
         * status code is 201
         * content type is json
         * name is MyTestMapPost
         * gender is Female
         * phone is 1237418520L
         */
        //jsonPath
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.contentType(), is("application/json"));
        JsonPath json = response.jsonPath();
        assertThat(json.getString("success"), is("A Spartan is Born!"));
        assertThat(json.getString("data.name"), is("MyTestMapPost"));
        assertThat(json.getString("data.gender"), is("Female"));
        assertThat(json.getLong("data.phone"), is(1237418520L));
        assertThat(json.getInt("data.id"), greaterThan(0));

        //Path
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("success"), is("A Spartan is Born!"));
        assertThat(response.path("data.name"), is("MyTestMapPost"));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(1237418520));
        assertThat(response.path("data.id"), greaterThan(0));
    }

    @Test
    public void postSpartanAndVerifyWithMapTest() {
    Map<String , Object> requestMap = new LinkedHashMap<>();

        requestMap.put("name" , "Ihor_Verf_Map");
        requestMap.put("gender" , "Male");
        requestMap.put("phone", 3455431234L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        Map<String , Object> responseMap = response.jsonPath().getMap("data");
        System.out.println("responseMap = " + responseMap);
//compare reaopnse data is
        assertThat(requestMap.get("name"), equalTo(requestMap.get("name")));
        assertThat(requestMap.get("gender"), equalTo(requestMap.get("gender")));
        assertThat(requestMap.get("phone"), equalTo(requestMap.get("phone")));
    }

        @Test
                public void postSpartanWithPojoTest(){
            //create object of spartan and set value
            Spartan reqSpartan = new Spartan();
            reqSpartan.setName("POJOpost");
            reqSpartan.setGender("Female");
            reqSpartan.setPhone(9877891234L);
//serialization: java object -> json
            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(reqSpartan).log().all() //automatically convert spartan object to json
                    .when().post("/api/spartans");

            System.out.println("response.statusCode() = " + response.statusCode());
            assertThat(response.statusCode(), is(201));
            assertThat(response.contentType(), is("application/json"));

            //verify json body
            //assign response spartan info into another Spartan object
            //then compare 2 spartan value
            //de-serialization = json => java object
            Spartan resSpartan = response.jsonPath().getObject("data", Spartan.class);

            //compare Spartan value
assertThat(resSpartan.getName(), equalTo(reqSpartan.getName()));
assertThat(resSpartan.getGender(), equalTo(reqSpartan.getGender()));
assertThat(resSpartan.getPhone(), equalTo(reqSpartan.getPhone()));

    }

}
