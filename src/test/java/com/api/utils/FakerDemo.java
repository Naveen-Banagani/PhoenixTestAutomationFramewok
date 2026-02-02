package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		System.out.println(firstName);
		String lastName = faker.name().lastName();
		System.out.println(lastName);
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().streetName());
		System.out.println(faker.address().city());
		System.out.println(faker.number().digits(14));
		System.out.println(faker.numerify("Study#####"));
		System.out.println(faker.internet().emailAddress());
		System.out.println(faker.phoneNumber().cellPhone());
	}

}
