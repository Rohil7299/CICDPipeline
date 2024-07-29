package com.ae.qa.tests.Sysadmin;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.Sysadmin.InformationPage;
import com.ae.qa.pages.TenantAdmin.InformationPageTA;
import com.ae.qa.util.ExcelHandler;

public class InformationPageTest extends TestBase {
	InformationPage informationpage;

	public InformationPageTest() {
		super();
	}


	@Test(priority=6)
	public void validateAboutTabTest(Method method) throws Exception {
		extentTest = extent.createTest("validateAboutTab", "TC_74: To verify About tab");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("TAsheetname"),method.getName());
		informationpage = new InformationPage();
		informationpage.aboutTab();
		extentTest.log(extentTest.getStatus(), "Verify About Tab");  
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("TAsheetname"), "Pass", method.getName());
	}
}
