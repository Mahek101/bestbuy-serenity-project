package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

@RunWith(SerenityRunner.class)
public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/stores").
                then().statusCode(200);
    }

    @Title("Verify the if the total is equal to 1563")
    @Test
    public void test01() {
        response.body("total", equalTo(1563));
    }

    @Title("Verify the if the stores of limit is equal to 10")
    @Test
    public void test02() {
        response.body("limit", equalTo(10));
    }

    @Title("Check the single ‘Name’ in the Array list (Inver Grove Heights)")
    @Test
    public void test03() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Title("Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)")
    @Test
    public void test04() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));

    }

    @Title("Verify the storied=7 inside storeservices of the third store of second services")
    @Test
    public void test05() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));

    }

    @Title("Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville")
    @Test
    public void test06() {
        response.body("data[2].services[0].storeservices", hasKey("createdAt"));
    }

    @Title("Verify the state = MN of forth store")
    @Test
    public void test07() {
        response.body("data[3].state", equalTo("MN"));
    }

    @Title("Verify the store name = Rochester of 9th store")
    @Test
    public void test08() {
        response.body("data[8].name", equalTo("Rochester"));

    }

    @Title("Verify the storeId = 11 for the 6th store")
    @Test
    public void test09() {
        response.body("data[5].id", equalTo(11));

    }

    @Title("Verify the serviceId = 4 for the 7th store of forth service")
    @Test
    public void test10() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }
}
