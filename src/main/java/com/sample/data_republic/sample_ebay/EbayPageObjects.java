package com.sample.data_republic.sample_ebay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.junit.Assert;

public class EbayPageObjects {

	WebElement searchTextBox;
	WebElement searchButton;
	WebElement shoppingCartIcon;
	WebElement addToCartButton;
	WebElement searchResult;
	WebElement viewCartIcon;
	String itemName;

	public void enterSearchText(WebDriver driver, String searchText) {
		searchTextBox = driver.findElement(By.id("gh-ac"));
		if (searchTextBox.isDisplayed()) {
			System.out.println("The searchbox is found");
			searchTextBox.click();
			searchTextBox.sendKeys(searchText);
		} else {
			System.out.println("The search Box is not found");
		}
	}

	/**
	 * @param driver
	 *            Method to click search button
	 */
	public void clickSearchButton(WebDriver driver) {
		searchButton = driver.findElement(By.cssSelector("#gh-btn"));
		if (searchButton.isDisplayed()) {
			searchButton.click();
		}
	}

	/**
	 * @param driver
	 *            Method to click addtocart button
	 */
	public void clickAddToCartButton(WebDriver driver) {
		addToCartButton = driver.findElement(By.cssSelector("#isCartBtn_btn"));
		if (addToCartButton.isDisplayed()) {
			addToCartButton.click();
		}
	}

	public void selectOptionFromList(WebDriver driver) {
		searchResult = driver.findElement(By.cssSelector("#ResultSetItems .li:nth-child(1) .lvtitle"));
		itemName = searchResult.getText();
		System.out.println("Item Name:" + itemName);
		searchResult.click();
		try {
			if (driver.findElement(By.id("msku-sel-1")).isDisplayed()) {
				Select oSelect = new Select(driver.findElement(By.id("msku-sel-1")));
				oSelect.selectByValue("0");
				clickAddToCartButton(driver);
			}
		} catch (Exception e) {
			clickAddToCartButton(driver);
		}
	}

	public void clickViewCartIcon(WebDriver driver) {
		viewCartIcon = driver.findElement(By.id("gh-cart-i"));
		viewCartIcon.click();
	}

	public void verifyItemsAvailable(WebDriver driver, String product1, String product2) {
		boolean item1Available = driver
				.findElement(By.xpath("//a[contains(text(),'"+product1+"')]")).isDisplayed();
		Assert.assertTrue(item1Available);

		boolean item2Available = driver
				.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]")).isDisplayed();
		Assert.assertTrue(item2Available);

	}

}
