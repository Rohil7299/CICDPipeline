package com.ae.qa.tests.Sysadmin;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.Sysadmin.LoginPage;
import com.ae.qa.pages.Sysadmin.TenantsPage;
import com.ae.qa.util.ExcelHandler;
import com.aventstack.extentreports.Status;

public class LoginPageTest extends TestBase {
	LoginPage loginpage;

	// to initialize the prop files in TestBase class
	public LoginPageTest() {
		super();
	}

	  @Test(priority=1,alwaysRun=true) 
	  public void ValidateFirstTimeLoginTest(Method method) throws Exception { 
	  extentTest = extent.createTest( "ValidateFirstTimeLoginTest","TC_1: Verify sysadmin user able to change first time password");
	  Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
	  loginpage = new LoginPage();
	  loginpage.ValidateFirstTimeLogin(prop.getProperty("username"),prop.getProperty("FT_password"),prop.getProperty("password"));
	  extentTest.log(extentTest.getStatus(),"User is able to change first time password of sysadmin.");
	  ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName()); 
	  }
}