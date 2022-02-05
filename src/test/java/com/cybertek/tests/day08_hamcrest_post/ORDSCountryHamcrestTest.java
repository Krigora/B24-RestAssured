package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Country;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

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

        Country zimbabwe = given().accept(ContentType.JSON)
                .when().get("/countries/ZW")
                .then().assertThat().statusCode(200)
                     .and().contentType(ContentType.JSON).log().all()
             .and().extract().body().as(Country.class);

        System.out.println("zimbabwe = " + zimbabwe);

        assertThat(zimbabwe.getCountryName(), is("Zimbabwe"));

    }


}
