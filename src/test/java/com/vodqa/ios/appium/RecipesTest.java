package com.vodqa.ios.appium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vodqa.utils.BaseTest;

public class RecipesTest extends BaseTest {

	String addRecipe = "Add";
	String receipeName = "Recipe Name";
	String saveRecipe = "//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]";

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
