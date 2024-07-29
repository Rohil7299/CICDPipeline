package com.ae.qa.pages.Sysadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.ae.qa.base.TestBase;
import com.ae.qa.util.Messages;
import com.aventstack.extentreports.Status;

public class InformationPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 200);
	public LoginPage loginpage = new LoginPage();
	
	@FindBy(id="login-username")
	WebElement UserNameTab;
	@FindBy(xpath = "//span[contains(text(),'About')]")
	WebElement aboutTab;
	@FindBy(xpath = "//*[contains(text(),'Password updated successfully')]")
	WebElement success_msg;
	@FindBy(name="sign-out")
	WebElement signOutBtn;
	@FindBy(xpath="//button[@name='close']/span[1]")
	WebElement closeBtn;
	@FindBy(xpath="//button[@name='cancel']/span[1]")
	WebElement cancelBtn;

	public InformationPage() {
		PageFactory.initElements(driver, this);
	}

	
	public void validateSignOut() throws InterruptedException {
		//loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", UserNameTab);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", signOutBtn);
		System.out.println("User signed out successfully");
		
	}
	public void aboutTab() throws Exception
	{
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Reporter.log("User log in successfully",true);
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", UserNameTab);
		Thread.sleep(2000);
		aboutTab.click();
		Thread.sleep(3000);
		String actual_UIVersion=driver.findElement(By.xpath("//table/tr/td[text()='UI Version']/../td/b")).getText();
		String expected_UIVersion=prop.getProperty("UIVersion");
		String actual_ServerVersion=driver.findElement(By.xpath("//table/tr/td[text()='Server Version']/../td/b")).getText();
		String expected_ServerVersion=prop.getProperty("ServerVersion");
		System.out.println("Actual_UIVersion:- "+actual_UIVersion);
		System.out.println("Actual_ServerVersion:-"+actual_ServerVersion);
		Thread.sleep(3000);
		SoftAssert s=new SoftAssert();
		s.assertEquals(actual_UIVersion, expected_UIVersion,"UIVersion does not match");
		s.assertEquals(actual_ServerVersion, expected_ServerVersion,"Server Version does not match");
		s.assertAll();
		closeBtn.click();
		validateSignOut();

	}

}
