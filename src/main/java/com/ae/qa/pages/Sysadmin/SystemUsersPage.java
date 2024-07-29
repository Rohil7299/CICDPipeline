package com.ae.qa.pages.Sysadmin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ae.qa.base.TestBase;
import com.ae.qa.util.Messages;

public class SystemUsersPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 150);
	public LoginPage loginpage = new LoginPage();
	public InformationPage informationpage = new InformationPage();

	@FindBy(xpath = "//span[text()='Users']")
	WebElement usersTab;
	@FindBy(xpath = "//a[text()='User List']")
	WebElement systemUsersTab;
	@FindBy(xpath = "//span[(text()='Tenants')]")
	WebElement tenantsTab;
	@FindBy(id = "btnConsumtionbased")
	WebElement allotmentBasedTab;
	@FindBy(xpath = "//button[@name='add-new']")
	WebElement addBtn;
	@FindBy(id = "tenantOrgCode")
	WebElement tenantdrpdown;
	@FindBy(id = "fname")
	WebElement fName;
	@FindBy(id = "lname")
	WebElement lName;
	@FindBy(id = "useremail")
	WebElement userMail;
	@FindBy(id = "username")
	WebElement userName;
	@FindBy(id = "pswd")
	WebElement pswd;
	@FindBy(id = "confirmPswd")
	WebElement confirmPswd;
	@FindBy(xpath = "//*[contains(text(),'Passwords Mismatch!')]")
	WebElement confirmationError;
	@FindBy(xpath = "//button[@name='submit']")
	WebElement createBtn;
	@FindBy(xpath = "//span[@class='fa fa-refresh']")
	WebElement refreshBtn;
	@FindBy(xpath = "//button[@name='save' and @type='submit']")
	WebElement saveBtn;
	@FindBy(xpath = "//*[contains(text(),'User updated successfully')]")
	WebElement editUserMsg;
	@FindBy(xpath = "//div[@class='title-div']/h2")
	WebElement pageTitle;
	@FindBy(xpath = "//*[@class='alert-message-text ng-star-inserted']")
	WebElement alertMessage;
	@FindBy(id = "role")
	WebElement roledropdown;
	@FindBy(xpath = "//ae-multiselect[@id='tenantOrgCode']/div")
	WebElement tenantdrpdownlist;
	@FindBy(xpath = "//*[@id='uploadModal']/div/div/form/div[1]/fieldset/div/label/input")
	WebElement chooseFileFromDesktop;
	@FindBy(xpath = "//*[@class='alert-message-text ng-star-inserted']")
	WebElement successMsgBox;
	@FindBy(xpath = "//button[@id='uploadBtn']")
	WebElement uploadBtn;
	@FindBy(xpath = "//button[@id='popup-button-ok']")
	WebElement confirmDeleteBtn;

	public SystemUsersPage() {
		PageFactory.initElements(driver, this);
	}

	public void creation(String tenantOrgCode, String FName, String LName, String UserMail, String UserName,
			String Pswd, String ConfirmPswd, String RoleName) throws Exception {
		// Click Users Tab
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Reporter.log("User logged in successfully", true);
		// driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		// wait.until(ExpectedConditions.visibilityOf(usersTab));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", usersTab);
		// Click TenantUsers Tab
		// wait.until(ExpectedConditions.visibilityOf(systemUsersTab));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", systemUsersTab);
		// click add new
		Thread.sleep(2000);
		// wait.until(ExpectedConditions.visibilityOf(addBtn));
		js.executeScript("arguments[0].click();", addBtn);
		log.info("started creating new system admin");
		// Start form
		/*tenantdrpdownlist.click();
		//js.executeScript("arguments[0].click();", tenantdrpdownlist);
		Thread.sleep(3000);
		// Select select = new Select(tenantdrpdownlist);
		// select.selectByValue("SYSADMIN");
		WebElement select_tenant = driver.findElement(
				By.xpath("//div[@id='options-list']/li/label/span[contains(text(),'"+tenantOrgCode+"')]/../input/../span[2]"));
		select_tenant.click();
		tenantdrpdownlist.click();*/
		Thread.sleep(2000);
		fName.sendKeys(FName);
		Thread.sleep(2000);
		lName.sendKeys(LName);
		Thread.sleep(2000);
		userMail.sendKeys(UserMail);
		Thread.sleep(2000);
		userName.sendKeys(UserName);
		Thread.sleep(2000);
		pswd.sendKeys(Pswd);
		Thread.sleep(2000);
		confirmPswd.sendKeys(ConfirmPswd);
		Thread.sleep(4000);
		Select select_role = new Select(roledropdown);
		select_role.selectByVisibleText(RoleName);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", createBtn);
		Thread.sleep(3000);
	}

	public void creatingSystemAdmin(String tenantOrgCode, String FName, String LName, String UserMail, String UserName,
			String Pswd, String ConfirmPswd, String RoleName) throws Exception {
		creation(tenantOrgCode, FName, LName, UserMail, UserName, Pswd, ConfirmPswd, RoleName);
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_Msg = alertMessage.getText();
		String expected_Msg = Messages.creationOfUser;
		Assert.assertEquals(actual_Msg, expected_Msg, "User not created");
		Reporter.log("User is created successfully", true);
		Thread.sleep(5000);
		for (int i = 0; i <= 2; i++) {
			String actual_UserName = driver.findElement(By.xpath("//table/tr/td/label[@title='" + UserName + "']")).getText();
			String expected_UserName = UserName;
			System.out.println("Actual Username:" + actual_UserName);
			System.out.println("Expected Username:" + expected_UserName);
			Assert.assertEquals(actual_UserName, expected_UserName, "System Admin can not added in list");
			Reporter.log("System Admin is verified and present in the webtable", true);
			break;
		}
		informationpage.validateSignOut();
	}

	public void EditSystemUsers(String tenantOrgCode, String FName, String LName, String UserMail, String UserName,
			String Pswd, String ConfirmPswd, String RoleName, String NewUserMail) throws Exception {
		creation(tenantOrgCode, FName, LName, UserMail, UserName, Pswd, ConfirmPswd, RoleName);
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_Msg = alertMessage.getText();
		String expected_Msg = Messages.creationOfUser;
		Assert.assertEquals(actual_Msg, expected_Msg, "User not created");
		Reporter.log("User is created successfully", true);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table/tr/td/label[@title='" + UserName + "']")).click();
		driver.findElement(By.xpath("//table/tr/td/span")).click();
		System.out.println("clicking on edit user & editing emailID");
		for (int i = 0; i < 30; i++) {
			userMail.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(2000);
		userMail.sendKeys(NewUserMail);
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(editUserMsg));
		String actual_EditUserMsg = editUserMsg.getText();
		String expected_EditUserMsg = Messages.editSystemUser;
		System.out.println("Actual Username:" + actual_EditUserMsg);
		System.out.println("Expected Username:" + expected_EditUserMsg);
		Assert.assertEquals(actual_EditUserMsg, expected_EditUserMsg, "System User details not edited successfully");
		Reporter.log("System User details got edited.", true);
		informationpage.validateSignOut();

	}

}
