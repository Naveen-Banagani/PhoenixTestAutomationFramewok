package com.api.test;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {
	@Test
	public void countApiTest() throws IOException {
		Header authHeader = new Header("Authorization",getToken(FD));
		given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header(authHeader)
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success")) //this is Matchers method i am not writing Matchers because I used static imports
		.time(lessThan(5000L))
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
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
