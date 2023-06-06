package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.RestRequests.given;

@RunWith(SerenityRunner.class)
public class StoresExtractionTest {

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

    @Title("Extract the limit")
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Extract the total")
    @Test
    public void test02() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Extract the name of 5th store")
    @Test
    public void test03() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Name : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Extract the names of all the store")
    @Test
    public void test04() {
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Store Name is : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Extract the storeId of all the store")
    @Test
    public void test05() {
        List<Integer> allStoreID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Store ID  is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Print the size of the data list")
    @Test
    public void test06() {
        List<Objects> sizeofData = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Size of Data is : " + sizeofData.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get all the value of the store where store name = St Cloud")
    @Test
    public void test07() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of St Cloud : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get the address of the store where store name = Rochester")
    @Test
    public void test08() {
        List<String> addressOfRochester = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of  StoreName : " + addressOfRochester);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get all the services of 8th store")
    @Test
    public void test09() {
        List<String> allServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get storeServices of the store where service name = Windows Store")
    @Test
    public void test10() {
        List<String> storeService = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Services : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get all the storeId of all the store")
    @Test
    public void test11() {
        List<Integer> storeIDAll = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + storeIDAll);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Get id of all the store")
    @Test
    public void test12() {
        List<Integer> storeID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + storeID);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the store names Where state = ND")
    @Test
    public void test13() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Name : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the Total number of services for the store where store name = Rochester")
    @Test
    public void test14() {
        List<?> totalService = response.extract().path("data.find{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + totalService.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the createdAt for all services whose name = “Windows Store”")
    @Test
    public void test15() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Created At : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the name of all services Where store name = “Fargo”")
    @Test
    public void test16() {
        List<HashMap<String, ?>> totalService = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + totalService);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the zip of all the store")
    @Test
    public void test17() {
        List<HashMap<Objects, ?>> zipCode = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zipCode);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the zip of store name = Roseville")
    @Test
    public void test18() {
        List<HashMap<String, ?>> zipStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the storeServices details of the service name = Magnolia Home Theater")
    @Test
    public void test19() {
        List<HashMap<String, ?>> storeService = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store Service of Mongolia Home Theater : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    @Title("Find the lat of all the stores")
    @Test
    public void test20() {
        List<HashMap<Objects, ?>> latStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of All Store : " + latStore);
        System.out.println("------------------End of Test---------------------------");
    }
}
