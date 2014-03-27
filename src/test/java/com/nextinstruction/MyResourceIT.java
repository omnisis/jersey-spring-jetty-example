package com.nextinstruction;

import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;

public class MyResourceIT {


    @Before
    public void setupClient() {
        System.out.println("running integration test!");
    }

    @Test
    public void testGetPerson() {
        //get("/")

    }
}
