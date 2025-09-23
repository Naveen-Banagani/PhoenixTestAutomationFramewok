package com.api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	@Test
	public void loginApiTest() throws IOException {
			UserCredentials userCredentials = new UserCredentials("iamfd","password");
			given()
				.baseUri(getProperty("BASE_URI")) //calling getproperty() using class name because method is static and we are not using className because of static import
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
