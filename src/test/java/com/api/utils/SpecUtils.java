package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

public class SpecUtils {
	public static RequestSpecification requestSpec() throws IOException {
		RequestSpecification request = new RequestSpecBuilder()
											.setBaseUri(getProperty("BASE_URI"))//calling getproperty() using class name because method is static and we are not using className because of static import
											.setContentType(ContentType.JSON)
											.setAccept(ContentType.JSON)
											.log(LogDetail.URI)
											.log(LogDetail.METHOD)
											.log(LogDetail.HEADERS)
											.log(LogDetail.BODY)
											.build();
		return request;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role) throws IOException {
		RequestSpecification request = new RequestSpecBuilder()
											.setBaseUri(getProperty("BASE_URI"))//calling getproperty() using class name because method is static and we are not using className because of static import
											.setContentType(ContentType.JSON)
											.setAccept(ContentType.JSON)
											.addHeader("Authorization", AuthTokenProvider.getToken(role))
											.log(LogDetail.URI)
											.log(LogDetail.METHOD)
											.log(LogDetail.HEADERS)
											.log(LogDetail.BODY)
											.build();
		return request;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) throws IOException {
		RequestSpecification request = new RequestSpecBuilder()
											.setBaseUri(getProperty("BASE_URI"))//calling getproperty() using class name because method is static and we are not using className because of static import
											.setContentType(ContentType.JSON)
											.setAccept(ContentType.JSON)
											.addHeader("Authorization", AuthTokenProvider.getToken(role))
											.setBody(payload)
											.log(LogDetail.URI)
											.log(LogDetail.METHOD)
											.log(LogDetail.HEADERS)
											.log(LogDetail.BODY)
											.build();
		return request;
	}
	

	public static RequestSpecification requestSpec(Object payload) throws IOException {
		RequestSpecification request = new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))//calling getproperty() using class name because method is static and we are not using className because of static import
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		return request;
	}
	
	public static ResponseSpecification responseSpec() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
															.expectContentType(ContentType.JSON)
															.expectStatusCode(200)
															.expectResponseTime(Matchers.lessThan(5000L))
															.log(LogDetail.ALL)
															.build();
		return responseSpecification;
														
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
															.expectContentType(ContentType.JSON)
															.expectStatusCode(statusCode)
															.expectResponseTime(Matchers.lessThan(5000L))
															.log(LogDetail.ALL)
															.build();
		return responseSpecification;
														
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
															.expectStatusCode(statusCode)
															.expectResponseTime(Matchers.lessThan(5000L))
															.log(LogDetail.ALL)
															.build();
		return responseSpecification;
														
	}
	


}
