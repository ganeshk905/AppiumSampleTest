package com.vodqa.android.appium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vodqa.utils.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class WordPressTest extends BaseTest {

	String userName = "org.wordpress.android:id/nux_username";
	String password = "org.wordpress.android:id/nux_password";
	String signIn = "org.wordpress.android:id/nux_sign_in_button";
	String newPost = "org.wordpress.android:id/fab_button";
	String postTitle = "org.wordpress.android:id/post_title";
	String postContent = "org.wordpress.android:id/post_content";
	String savePost = "org.wordpress.android:id/menu_save_post";

	@Test
	public void logIn() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(userName)));
		driver.findElementById(userName).sendKeys("vodqa@gmail.com");
		driver.findElementById(password).sendKeys("Hello12345678");
		driver.findElementById(signIn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(newPost)));
		driver.findElementById(newPost).click();
		driver.findElementById(postTitle).sendKeys("AppiumTest");
		driver.findElementById(postContent).sendKeys("Description");
		((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
		driver.findElementById(savePost).click();
	}
}
