package com.ae.qa.pages.Sysadmin;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.ae.qa.base.TestBase;
import com.ae.qa.util.Messages;

public class TenantsPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 180);
	public LoginPage loginpage = new LoginPage();
	public InformationPage informationpage=new InformationPage();
	public TenantUsersPage tenantuserpage = new TenantUsersPage();

	@FindBy(xpath = "//h2")
	WebElement tenantsPageTitle;
	@FindBy(xpath = "//span[(text()='Tenants')]")
	WebElement tenantsTab;
	@FindBy(xpath = "//span[text()='Users']")
	WebElement usersTab;
	@FindBy(xpath = "//a[text()='Tenant Users']")
	WebElement tenantUsersTab;
	@FindBy(name = "add-tenant")
	WebElement addTenantBtn;
	@CacheLookup
	@FindBy(id = "tenantName")
	WebElement tenantName;
	@FindBy(id = "description")
	WebElement descriptionOfTenant;
	@FindBy(id = "orgCode")
	WebElement organizationCode;
	@FindBy(name = "submit")
	WebElement createBtn;
	@FindBy(name = "cancel")
	WebElement cancelBtn;
	@FindBy(xpath = "(//button[@name='cancel'])[2]")
	WebElement CancelBtn;
	@FindBy(xpath = "//*[@class='alert-message-text ng-star-inserted']")
	WebElement alertMessage;

	public TenantsPage() {
		PageFactory.initElements(driver, this);
	}


	public void addNewTenants(String tName, String tDescription, String orgCode) throws Exception {
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Reporter.log("User log in Successfully",true);
		// click Tenants Tab
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", tenantsTab);
		// Click add Tenant button
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", addTenantBtn);
		// Fill details like Tenant Name, Description and Organization code
		Thread.sleep(2000);
		tenantName.sendKeys(tName);
		Thread.sleep(3000);
		descriptionOfTenant.sendKeys(tDescription);
		wait.until(ExpectedConditions.visibilityOf(organizationCode));
		organizationCode.sendKeys(orgCode);
		// Create button
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", createBtn);
		Thread.sleep(2000);
		CancelBtn.click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", tenantsTab);
		Thread.sleep(2000);
		// Verify data in table now
		Reporter.log("Below validation is to validate new tenant record is visible in webtable",true);
		String actual_TenantName = driver.findElement(By.xpath("//table/tr/td[text()='" + tName + "']")).getText();
		String expected_TenantName = tName;
		System.out.println("Actual:"+actual_TenantName+"Expected:"+expected_TenantName);
		Assert.assertEquals(actual_TenantName, expected_TenantName, "Tenant can not added in list");
		Reporter.log("New Tenant is present in the table-Validated successfully",true);
		informationpage.validateSignOut();
	}
}

