package com.ae.qa.pages.Sysadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ae.qa.base.TestBase;
import com.ae.qa.util.Messages;


public class LoginPage extends TestBase {
	public static WebDriverWait wait = new WebDriverWait(driver, 120);

	// PageFactory
	@FindBy(id = "uname")
	WebElement username;
	@FindBy(id = "pswd")
	WebElement password;
	@FindBy(id = "signin1")
	WebElement signInBtn;
	@FindBy(id = "change-pswd")
	WebElement changePswd;
	@FindBy(xpath = "//div[@id='login-username']")
	WebElement UserNameTab;
	@FindBy(xpath = "//button[text()='Change']")
	WebElement changeBtn;
	@FindBy(xpath = "//*[@class='alert-message-text ng-star-inserted']")
	WebElement PopUpMsg;
	@FindBy(xpath="//input[@id='oldpswd']")
	WebElement oldPswd;
	@FindBy(xpath="//input[@id='newpswd']")
	WebElement newPswd1;
	@FindBy(xpath="//input[@id='confirmpswd']")
	WebElement newConfirmPswd;
	
	
	// initialize all this Object Repository
	public LoginPage() {
		PageFactory.initElements(driver, this);

	}

	// Actions
	public void login(String un, String pswd) throws Exception {
		username.sendKeys(un);
		password.sendKeys(pswd);
	//	wait.until(ExpectedConditions.visibilityOf(signInBtn));
		Thread.sleep(3000);
		signInBtn.click();
	}
	
	public void ValidateFirstTimeLogin(String Username,String FT_password,String password) throws Exception {
		//sysadmin user login with first time password
		login(Username,FT_password); 
		Thread.sleep(2000);
		//Change password with new password
		oldPswd.sendKeys(FT_password);
		Thread.sleep(2000);
		newPswd1.sendKeys(password);
		Thread.sleep(2000);
		newConfirmPswd.sendKeys(password);
		Thread.sleep(2000);
		JavascriptExecutor js_change=(JavascriptExecutor)driver;
		js_change.executeScript("arguments[0].click();", changeBtn);
		Reporter.log("User changed first time password of sysadmin Successfully",true);
	}
}
