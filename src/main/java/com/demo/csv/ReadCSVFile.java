package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
//		File csvFile = new File("C:\\Users\\navee\\eclipse-workspace\\PhoenixApiTestAutomationFrameword\\src\\main\\resources\\testData\\loginCreds.csv");
//		FileReader fr = new FileReader(csvFile);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); //CSVReader constructor requires READER
		List<String[]> dataList = csvReader.readAll();
		for(String[] dataArray: dataList) {
			for(String data:dataArray) {
				System.out.print(data+" ");
			}
			System.out.println(" ");
		}
		
		
	}

}
