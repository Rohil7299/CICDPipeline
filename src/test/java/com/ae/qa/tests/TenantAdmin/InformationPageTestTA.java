package com.ae.qa.tests.TenantAdmin;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.TenantAdmin.InformationPageTA;
import com.ae.qa.util.ExcelHandler;
import com.ae.qa.util.TestUtil;

public class InformationPageTestTA extends TestBase {
	InformationPageTA informationpageta;

	public InformationPageTestTA() {
		super();
	}
	//No data required

	@Test(priority=8)
	public void validateAboutTabTest(Method method) throws Exception {
		extentTest = extent.createTest("validateAboutTab", "TC_45: To verify About tab");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("TAsheetname"),method.getName());
		informationpageta = new InformationPageTA();
		informationpageta.aboutTab();
		extentTest.log(extentTest.getStatus(), "Verify About Tab");  
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("TAsheetname"), "Pass", method.getName());
	}
}
