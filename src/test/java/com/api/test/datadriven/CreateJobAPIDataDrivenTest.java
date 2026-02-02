package com.api.test.datadriven;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.dataproviders.api.bean.CreateJobBean;

import io.restassured.RestAssured;

public class CreateJobAPIDataDrivenTest {
	
	@Test(description= "Verifying if CreateJob API is working", groups = {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {
		//Creating the CreateJobPayload object
				RestAssured.given()
					  .spec(requestSpecWithAuth(Role.FD, createJobPayload))
					  .when()
					  .post("/job/create")
					  .then()
					  .spec(responseSpec())
					  .body(matchesJsonSchemaInClasspath("response-schema/createJobApiResponseSchema.json"))
					  .body("message", Matchers.equalTo("Job created successfully. "))
					  .body("data.mst_service_location_id", Matchers.equalTo(1))
					  .body("data.job_number",Matchers.startsWith("JOB_"));
				
				System.out.println(createJobPayload.mst_platform_id());

	}

}
