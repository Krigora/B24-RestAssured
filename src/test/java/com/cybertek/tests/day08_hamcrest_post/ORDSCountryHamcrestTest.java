package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Country;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.xml.ws.Response;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSCountryHamcrestTest extends ORDSTestBase {

    /**
     * send get request to /ords/hr/countries/ZW
     * verify status code is 200
     * header is json
     * and extract body as Country pojo object
     * Country country = ....
     */


    @Test
    public void getCountryToPojoTest() {

        Country country = given().accept(ContentType.JSON)
                .when().get("/countries/ZW")
                .then().assertThat().statusCode(200)
                     .and().contentType(ContentType.JSON).log().all()
             .and().extract().body().as(Country.class);


//         Response response =  given().accept(ContentType.JSON)
//                .when().get("/countries/ZW");
//
//        assertThat(response.path("items.country_name"), hasItems("Zimbabwe"));

   assertThat(country.getCountryName(), is("Zimbabwe"));
//.and().body("country_id",equalTo( "ZW"),"country_name",equalTo("Zimbabwe"),"region_id",equalTo(4));
//        @Test
//        public void getCountryToPojoTest() {
//            Country country = given().accept(ContentType.JSON)
//                    .when().get("/countries/ZW")
//                    .then().assertThat().statusCode(200)
//                    .and().contentType(ContentType.JSON)
//                    .and().extract().body().as(Country.class);
//
//            System.out.println("country = " + country);
//        }
    }


    }



