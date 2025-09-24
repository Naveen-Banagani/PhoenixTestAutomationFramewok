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

public class MasterApiTest {
	@Test
	public void masterApiTest() throws IOException {
		Header authHeader = new Header("Authorization",getToken(FD));
		given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header(authHeader)
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success")) //this is Matchers method i am not writing Matchers because I used static imports
		.time(lessThan(5000L))
		.body("data", notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterResponseSchema.json"));

	}
	
	@Test
	public void MasterApiTest_MissingAuth() throws IOException {
		given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(401);
	}

}
