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

@RunWith(SerenityRunner.class)
public class ProductAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/products").
                then().statusCode(200);
    }

    @Title("Verify the if the total is equal to 51957")
    @Test
    public void test01() {
        response.body("total", equalTo(51957));
    }

    @Title("Verify the if the stores of limit is equal to 10")
    @Test
    public void test02() {
        response.body("limit", equalTo(10));
    }

    @Title("Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))")
    @Test
    public void test03() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    @Title("Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))")
    @Test
    public void test04() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"
                , "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Title("Verify the productId=150115 inside categories of the third name is “Household Batteries”")
    @Test
    public void test05() {
        response.body("data.find{it.id == 150115}.categories[2].name", equalTo("Household Batteries"));
    }

    @Title("Verify the categories second name = “Housewares” of productID = 333179")
    @Test
    public void test06() {
        response.body("data.find{it.id == 333179}.categories[1].name", equalTo("Housewares"));
    }

    @Title("Verify the price = 4.99 of forth product")
    @Test
    public void test07() {
        response.body("data[3].price", equalTo(4.99f));
    }

    @Title("Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product")
    @Test
    public void test08() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    @Title("Verify the ProductId = 333179 for the 9th product")
    @Test
    public void test09() {
        response.body("data[8].id", equalTo(333179));
    }

    @Title("Verify the productId = 346575 have 5 categories")
    @Test
    public void test10() {
        response.body("data.find{it.id == 346575}.categories.size()", equalTo(5));
    }
}
