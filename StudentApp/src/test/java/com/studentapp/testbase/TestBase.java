package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost:8080/student";
    }
}
