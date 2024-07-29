package com.ae.qa.tests.Sysadmin;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.Sysadmin.SystemUsersPage;
import com.ae.qa.pages.Sysadmin.TenantUsersPage;
import com.ae.qa.util.ExcelHandler;
import com.ae.qa.util.TestUtil;

public class TenantUsersPageTest extends TestBase {
	TenantUsersPage tenantuserspage;
	String sheetName = "TenantUserData";

	public TenantUsersPageTest() {
		super();
	}

	@Test(priority = 5)
	public void ValidateEditTenantUserTest(Method method) throws Exception {
		extentTest = extent.createTest("ValidateEditTenantUserTest", "TC_016: To verify Edit Tenant User");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		tenantuserspage = new TenantUsersPage();
		tenantuserspage.EditTenantUser(TestDataInMap.get("OrganizationCode"),TestDataInMap.get("FirstName"),TestDataInMap.get("LastName"),TestDataInMap.get("EmailId"),TestDataInMap.get("UserName"),
				TestDataInMap.get("Password"),TestDataInMap.get("CnfPassword"),TestDataInMap.get("Role"),TestDataInMap.get("NewEmailID"));	
		extentTest.log(extentTest.getStatus(), "New Tenant Admin created & edited its emailID successfully.");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
}
