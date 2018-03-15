package com.studentapp.junit.studentsinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    @WithTag("studentfeature:NEGATIVE")
    @Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
    @Test
    public void inValidMethod() {
        SerenityRest.
            rest().given().when()
            .post("/list")
            .then()
            .statusCode(405)
            .log().all();
    }



    @WithTags(
        {
            @WithTag("studentfeature:SMOKE"),
            @WithTag("studentfeature:POSITIVE")
        }
    )
    @Title("This test will verify if a status code of 200 is returned for GET request")
    public void verifyIftheStatusCodeIs200() {
        SerenityRest.
            rest().given().when()
            .get("/list")
            .then()
            .statusCode(200);
    }


    @WithTags(
        {
            @WithTag("studentfeature:SMOKE"),
            @WithTag("studentfeature:NEGATIVE")
        }
    )
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void incorrectResource() {
        SerenityRest.
            rest().given().when()
            .get("/listsfkh")
            .then()
            .statusCode(400)
            .log().all();
    }
}
