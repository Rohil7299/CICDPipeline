package com.ae.qa.tests.Sysadmin;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.Sysadmin.LoginPage;
import com.ae.qa.pages.Sysadmin.TenantsPage;
import com.ae.qa.util.ExcelHandler;
import com.ae.qa.util.ReadExcel;
import com.ae.qa.util.TestDataHandler;
import com.ae.qa.util.TestUtil;

import com.aventstack.extentreports.gherkin.model.Scenario;


public class TenantsPageTest extends TestBase {
	LoginPage loginpage;
	TenantsPage tenantspage;
	TestDataHandler testdata=new TestDataHandler();

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public TenantsPageTest() {
		super();
	}
	//Here we are eliminating hard-coded value and adopting data driven approach
	@Test(priority = 2)
	public void validateAddNewTenant(Method method) throws Exception {
		extentTest = extent.createTest("validateAddNewTenant", "TC_001: To Verfiy Add new Tenant");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		tenantspage = new TenantsPage();
		System.out.println("Values from excel:"+TestDataInMap.get("TenantName")+TestDataInMap.get("Description")+TestDataInMap.get("OrganizationCode"));
		tenantspage.addNewTenants(TestDataInMap.get("TenantName"), TestDataInMap.get("Description"),TestDataInMap.get("OrganizationCode"));
		extentTest.log(extentTest.getStatus(), "Tenant added successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
}
