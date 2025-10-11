package com.api.test.datadriven;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiDataDrivenTest {
	
	
	@Test(description= "Verifying if login API is working for Iamfd", groups = {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIDataProvider")
	public void loginApiTest(UserBean userBean) {
			given()
				.spec(requestSpec(userBean))
			.when()
				.post("login")
			.then()
				.spec(responseSpec())
				.body("message", equalTo("Success"))
			    .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
			
	}

}
