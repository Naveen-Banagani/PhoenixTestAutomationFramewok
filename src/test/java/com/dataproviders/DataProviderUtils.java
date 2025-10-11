package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name = "LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPiDataProvider() {
		//Data Provider needs to return something!!
		//[] 1-D Array
		//[][] 2-D Array
		//Iterator<>
		return CSVReaderUtil.loadCsv("testData/loginCreds.csv");
	}
}
