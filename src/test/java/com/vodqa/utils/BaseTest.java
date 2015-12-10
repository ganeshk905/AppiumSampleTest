package com.vodqa.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	Properties prop = new Properties();
	InputStream input = null;

	@Before
	public void setUp() throws Exception {
		// Loads the properties file 
		input = new FileInputStream("config.properties");
		prop.load(input);
		if (prop.getProperty("runner").equalsIgnoreCase("ios")) {
			iosSetup();
		} else {
			androidSetup();
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	public void androidSetup() throws MalformedURLException {
		// set up appium for android instance
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Nexus 5");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "org.wordpress.android");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "org.wordpress.android.ui.WPLaunchActivity");
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/wordpress.apk");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 30);
	}

	public void iosSetup() throws MalformedURLException {
		// set up appium for ios instance
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/build/Recipes.app");
		driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 30);
	}

}
