package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.demo.csv.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	//All utils classes will have private constructors
	//All methods in utils class are static
	//Job: Help me read the csv file and map it to a Bean(POJO)
	private CSVReaderUtil() {
		//private constructor-restricts object creation in another class
	}
	
	public static void loadCsv(String pathToCSVFile) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); //CSVReader constructor requires READER
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
											.withType(UserBean.class)
											.withIgnoreEmptyLine(true)
											.build();
		
		List<UserBean> userList = csvToBean.parse();
		System.out.println(userList.get(0).getUsername()); //gives me iamfd from csv file
		
		
	}

}
