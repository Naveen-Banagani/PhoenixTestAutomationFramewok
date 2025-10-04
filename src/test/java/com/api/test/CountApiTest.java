package com.api.test;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {
	@Test
	public void countApiTest() throws IOException {
		given()
        .spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec())
		.body("message", equalTo("Success")) //this is Matchers method i am not writing Matchers because I used static imports
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body("data.key",containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/countResponseSchema.json"));

	}
	
	@Test
	public void countApiTest_MissingAuth() throws IOException {
		given()
		.spec(SpecUtils.requestSpec())
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec_TEXT(401));
	}

}
