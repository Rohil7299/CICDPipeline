package com.ae.qa.tests.Sysadmin;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.Sysadmin.SystemUsersPage;
import com.ae.qa.pages.Sysadmin.TenantsPage;
import com.ae.qa.util.ExcelHandler;
import com.ae.qa.util.TestUtil;

public class SystemUsersPageTest extends TestBase {
	SystemUsersPage systemuserspage;

	public SystemUsersPageTest() {
		super();
	}

	@Test(priority = 3)
	public void creatingSystemAdminTest(Method method) throws Exception {
		extentTest = extent.createTest("creatingSystemAdminTest", "TC_008: Verify create System User");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		systemuserspage = new SystemUsersPage();
		systemuserspage.creatingSystemAdmin(TestDataInMap.get("OrganizationCode"),TestDataInMap.get("FirstName"), TestDataInMap.get("LastName"),TestDataInMap.get("EmailId"),
				TestDataInMap.get("UserName"), TestDataInMap.get("Password"),TestDataInMap.get("CnfPassword"),TestDataInMap.get("Role"));
		extentTest.log(extentTest.getStatus(), "System User created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());

	}

	@Test(priority = 4)
	public void validateEditSystemUsers(Method method) throws Exception {
		extentTest = extent.createTest("ValidateEditSystemUsersTest", "TC_009: Verify Edit System User");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		systemuserspage = new SystemUsersPage();
		systemuserspage.EditSystemUsers(TestDataInMap.get("OrganizationCode"),TestDataInMap.get("FirstName"), TestDataInMap.get("LastName"),TestDataInMap.get("EmailId"),
				TestDataInMap.get("UserName"), TestDataInMap.get("Password"),TestDataInMap.get("CnfPassword"),TestDataInMap.get("Role"), TestDataInMap.get("NewEmailID"));
		extentTest.log(extentTest.getStatus(), "System User edited successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
}
