package com.api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	@Test
	public void loginApiTest() throws IOException {
			UserCredentials userCredentials = new UserCredentials("iamfd","password");
			given()
				.spec(SpecUtils.requestSpec(userCredentials))
			.when()
				.post("login")
			.then()
				.spec(SpecUtils.responseSpec())
				.body("message", equalTo("Success"))
			    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
			
	}

}
