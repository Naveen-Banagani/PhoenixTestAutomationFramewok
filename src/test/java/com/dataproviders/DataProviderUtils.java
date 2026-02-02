package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name = "LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPiDataProvider() {
		//Data Provider needs to return something!!
		//[] 1-D Array
		//[][] 2-D Array
		//Iterator<>
		return CSVReaderUtil.loadCsv("testData/loginCreds.csv", UserBean.class);
	}
	
	@DataProvider(name = "CreateJobAPIDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> CreateJobAPIDataProvider() {
	
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCsv("testData/CreateJobData.csv", CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while(createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
			
		}
		return payloadList.iterator();
	}
	
	//Demo purpose
//	public static void main(String[] args) {
//		Iterator<CreateJobBean> iterator = CSVReaderUtil.loadCsv("testData/CreateJobData.csv", CreateJobBean.class);
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//	}
}
