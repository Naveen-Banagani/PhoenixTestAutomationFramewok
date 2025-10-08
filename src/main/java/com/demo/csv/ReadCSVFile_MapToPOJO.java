package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
//		File csvFile = new File("C:\\Users\\navee\\eclipse-workspace\\PhoenixApiTestAutomationFrameword\\src\\main\\resources\\testData\\loginCreds.csv");
//		FileReader fr = new FileReader(csvFile);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); //CSVReader constructor requires READER
		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader)
											.withType(UserPOJO.class)
											.withIgnoreEmptyLine(true)
											.build();
		
		List<UserPOJO> userList = csvToBean.parse();
		System.out.println(userList.get(0).getUsername()); //gives me iamfd from csv file
		
		
	}

}
