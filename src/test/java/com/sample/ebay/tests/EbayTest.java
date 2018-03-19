package com.sample.ebay.tests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sample.ebay.helper.ScreenshotHelper;
import com.sample.ebay.pageObjects.EbayPageObjects;

/**
 * @author manokugan
 *
 */
public class EbayTest extends EbayPageObjects {

	public RemoteWebDriver driver;
	Properties propertyObj;

	@Before
	public void loadBrowser() throws MalformedURLException {
		String browser = TestRunner.browserOri;

		String Node = propertyObj.getProperty("node");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL(Node), cap);
		} else if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL(Node), cap);
		}
		propertyObj = readPropertyFile();
		driver.get(propertyObj.getProperty("url"));
	}

	@Given("^Ebay Page is Loaded$")
	public void ebay_Page_is_Loaded() throws Throwable {
		Assert.assertEquals("The page title should have the ebay title at the start of the test.",
				propertyObj.getProperty("title"), driver.getTitle());
	}

	@When("^Search Product \"([^\"]*)\"$")
	public void search_Product(String productName) throws Throwable {
		enterSearchText(driver, productName);
		clickSearchButton(driver);

	}

	@Then("^add the option to cart$")
	public void choose_the_option() throws Throwable {
		selectOptionFromList(driver);
	}

	@When("^click viewCart icon$")
	public void i_click_viewCart_icon() throws Throwable {
		clickViewCartIcon(driver);
	}

	@Then("^The items \"([^\"]*)\" and \"([^\"]*)\" are available$")
	public void the_items_and_are_available(String product1, String product2) throws Throwable {
		verifyItemsAvailable(driver, product1, product2);

	}

	@After
	public void tearDown() throws IOException {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date());
		ScreenshotHelper.saveScreenshot(fileName, driver);
		driver.quit();
	}

	private Properties readPropertyFile() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/properties/config.properties");
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}
