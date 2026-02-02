package com.api.test;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import com.github.javafaker.Faker;

import io.restassured.RestAssured;

public class CreateJobAPITest2 {
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY = "India";
	@BeforeMethod(description = "Creating Job api request payload")
	public void setUp() {
		Faker faker = new Faker(new Locale("en-IND"));// Help me to create India Specific Fake Data
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNumber = faker.numerify("788#######");
		String alternatemobileNumber = faker.numerify("788#######");
		String emailAddress = faker.internet().emailAddress();
		String altemailAddress = faker.internet().emailAddress();
		
		Customer customer = new Customer(firstName, lastName, mobileNumber, alternatemobileNumber, emailAddress, altemailAddress);
//		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landMark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.number().digits(5);
		
		String state = faker.address().state();
		
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landMark, area, pincode, COUNTRY, state);
//		System.out.println(customerAddress);
		
		//Customer Product Fake Object
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.number().digits(14);
		String popUrl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, 1, 1);
		System.out.println(customerProduct);
		String fakeRemark = faker.lorem().sentence(5);//random 10 words sentence
		//I want to generate random number between 1 to 27
		Random random = new Random();
		int problemId = random.nextInt(26)+1;//excludes 26 and adds plus 1 if random number is 2 then it will be 2+1 3. So we get 1 to 27 this excludes 0, 28 and above
		Problems problems = new Problems(problemId, fakeRemark);
//		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
	}
	
	@Test
	public void createJobApiTest() {
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

	}

}
