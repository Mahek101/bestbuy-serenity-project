package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {

    @Step("Creating Store with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9} and services {10}")
    public ValidatableResponse createStores(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, StoresPojo.Services services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        StoresPojo.Services service = new StoresPojo.Services();
        service.setServices(service);
        return SerenityRest.given().log().all()
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORE)
                .then().log().all();
    }

    @Step("Getting Store with id: {0}")
    public ValidatableResponse getStores(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Updating Store with with id: {0}, name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10} and services {11}")
    public ValidatableResponse updateStores(int id, String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, StoresPojo.Services services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        StoresPojo.Services service = new StoresPojo.Services();
        service.setServices(service);
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Deleting store with id: {0}")
    public ValidatableResponse deleteStores(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
}
