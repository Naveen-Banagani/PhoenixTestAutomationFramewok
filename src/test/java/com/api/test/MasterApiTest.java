package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterApiTest {
	@Test(description= "Verifying if Master API is giving correct response", groups = {"api","regression","smoke"})
	public void masterApiTest() {

		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(responseSpec())
		.body("message", equalTo("Success")) //this is Matchers method i am not writing Matchers because I used static imports
		.body("data", notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/masterResponseSchema.json"));

	}
	
	@Test(description= "Verifying if master API is giving correct status code for invalid token", groups = {"api","regression","smoke", "negative"})
	public void MasterApiTest_MissingAuth(){
		given()
		.spec(requestSpec())
		.when()
		.post("master")
		.then()
		.spec(responseSpec_TEXT(401));
	}

}
