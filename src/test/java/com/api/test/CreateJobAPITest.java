package com.api.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	@Test
	public void createJobApiTest() throws IOException {
		//Creating the CreateJobPayload object
		Customer customer = new Customer("Nav", "Ban", "1231231231", "", "navban@gmail.com", "");
		CustomerAddress customer_address = new CustomerAddress("2", "myHouse", "myStreet", "nearHouse", "myArea", "517500", "India", "AndhraPradesh");
		CustomerProduct customer_product = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "87415282927499", "87415282927499", "87415282927499", DateTimeUtil.getTimeWithDaysAgo(10), 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customer_product, problemsList);
		RestAssured.given()
					  .spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
					  .when()
					  .post("/job/create")
					  .then()
					  .spec(SpecUtils.responseSpec())
					  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/createJobApiResponseSchema.json"))
					  .body("message", Matchers.equalTo("Job created successfully. "))
					  .body("data.mst_service_location_id", Matchers.equalTo(1))
					  .body("data.job_number",Matchers.startsWith("JOB_"));

	}

}
