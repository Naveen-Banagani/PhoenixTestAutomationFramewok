package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountApiTest {
	@Test(description= "Verifying if count API is giving correct response", groups = {"api","regression","smoke"})

	public void countApiTest() throws IOException {
		given()
        .spec(requestSpecWithAuth(FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(responseSpec())
		.body("message", equalTo("Success")) //this is Matchers method i am not writing Matchers because I used static imports
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body("data.key",containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(matchesJsonSchemaInClasspath("response-schema/countResponseSchema.json"));

	}
	
	@Test(description= "Verifying if count API is giving correct status code for invalid token", groups = {"api","regression","smoke", "negative"})
	public void countApiTest_MissingAuth() throws IOException {
		given()
		.spec(requestSpec())
		.when()
		.get("dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));
	}

}
