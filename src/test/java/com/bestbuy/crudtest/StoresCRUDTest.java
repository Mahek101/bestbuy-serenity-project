package com.bestbuy.crudtest;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {

    String name = "ABC" + TestUtils.getRandomValue();
    String type = TestUtils.getRandomValue() + "Pharmacy";
    String address = "12" + TestUtils.getRandomValue();
    String address2 = "Harrow" + TestUtils.getRandomValue();
    String city = "london";
    String state = "England";
    String zip = "HA5 5AH";
    int lat = 123;
    int lng = 456;
    String hours = "Monday - Friday : 9AM. to 5PM.; Saturday: 9AM. to 4PM.; Sunday: CLOSED";
    StoresPojo.Services services;
    static int id;

    @Steps
    StoresSteps storesSteps;

    @Title("Creating store and verifying the creation.")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createStores(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));

    }

    @Title("Getting store information by id.")
    @Test
    public void test002() {
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));
    }

    @Title("Updating store information by id.")
    @Test
    public void test003() {
        name = name + "-Updated";
        storesSteps.updateStores(id, name, type, address, address2, city, state, zip, lat, lng, hours, services);
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Deleting the store and verifying the deletion.")
    @Test
    public void test004() {
        storesSteps.deleteStores(id).statusCode(200);
        storesSteps.getStores(id).statusCode(404);
    }
}
