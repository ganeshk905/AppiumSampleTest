package com.vodqa.android.appium;

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
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

public class WordPressTest  {

	String userName = "org.wordpress.android:id/nux_username";
	String password = "org.wordpress.android:id/nux_password";
	String signIn = "org.wordpress.android:id/nux_sign_in_button";
	String newPost = "org.wordpress.android:id/fab_button";
	String postTitle = "org.wordpress.android:id/post_title";
	String postContent = "org.wordpress.android:id/post_content";
	String savePost = "org.wordpress.android:id/menu_save_post";
	
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	@Before
	public void setUp() throws Exception {
			// set up appium for android instance
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "Nexus 5");
			capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "org.wordpress.android");
			capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "org.wordpress.android.ui.WPLaunchActivity");
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/wordpress.apk");
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wait = new WebDriverWait(driver, 30);
		}


	

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void logIn() throws InterruptedException {
		// Login to the Wordpress Application
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(userName)));
		driver.findElementById(userName).sendKeys("vodqa@gmail.com");
		driver.findElementById(password).sendKeys("Hello12345678");
		driver.findElementById(signIn).click();
		
		//Wait for the new post button and add a post
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(newPost)));
		driver.findElementById(newPost).click();
		driver.findElementById(postTitle).sendKeys("AppiumTest");
		driver.findElementById(postContent).sendKeys("Description");
		((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
		driver.findElementById(savePost).click();
	}
}
