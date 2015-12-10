package com.vodqa.ios.appium;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class RecipesTest{

	String addRecipe = "Add";
	String receipeName = "Recipe Name";
	String saveRecipe = "//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]";

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;

	@Before
	public void setUp() throws Exception {
		// set up appium for android instance
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/Recipes.app");
		driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 30);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void addRecipe() throws InterruptedException {

		// click on add recipe (using accessibility id)
		driver.findElementByAccessibilityId(addRecipe).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(receipeName)));

		// add recipe (using id)
		driver.findElementById(receipeName).sendKeys("TestRecipe");

		// save recipe (using xpath)
		driver.findElementByXPath(saveRecipe).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Recipes")));

		// navigate to home page
		driver.findElementByAccessibilityId("Recipes").click();

	}
}
