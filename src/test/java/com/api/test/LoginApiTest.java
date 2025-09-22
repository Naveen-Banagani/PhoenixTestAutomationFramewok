package com.api.test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

//import java.io.File;
//
//import static org.hamcrest.Matchers.*;
//
//import io.restassured.http.ContentType;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
//import io.restassured.response.Response;
public class LoginApiTest {
	@Test
	public void loginApiTest() {
			UserCredentials userCredentials = new UserCredentials("iamfd","password");
			given()
				.baseUri("http://64.227.160.186:9000/v1")
			.and()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userCredentials)
				.log().uri()
				.log().method()
				.log().headers()
				.log().body()
			.when()
				.post("login")
			.then()
				.log().all()
				.statusCode(200)
				.time(lessThan(5000L))
				.body("message", equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
			
	}

}
