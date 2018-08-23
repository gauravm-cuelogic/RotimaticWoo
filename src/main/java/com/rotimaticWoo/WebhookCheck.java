package com.rotimaticWoo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class WebhookCheck {

	WebDriver driver;
	By username = By.xpath("//INPUT[@id='user_login']");
	By password = By.xpath("//INPUT[@id='user_pass']");
	By logIn = By.xpath("//INPUT[@id='wp-submit']");
	By setting = By.xpath("//A[@href='admin.php?page=wc-settings'][text()='Settings']");
	By API = By
			.xpath("//A[@href='https://dev3.rotimatic.com/wp-admin/admin.php?page=wc-settings&tab=api'][text()='API']");
	By webhooks = By.xpath(
			"//A[@href='https://dev3.rotimatic.com/wp-admin/admin.php?page=wc-settings&tab=api&section=webhooks'][text()='Webhooks']");
	By Order_Updated = By.xpath(
			"//A[@href='https://dev3.rotimatic.com/wp-admin/admin.php?page=wc-settings&tab=api&section=webhooks&edit-webhook=268851'][text()='Order updated Migration Engine']");
	By Order_Created = By.xpath(
			"//A[@href='https://dev3.rotimatic.com/wp-admin/admin.php?page=wc-settings&tab=api&section=webhooks&edit-webhook=268849'][text()='Order Created Migration Engine']");
	By Order_Updated_Status = By.xpath("//*[@id=\"the-list\"]/tr[1]/td[2]");
	By Order_Created_Status = By.xpath("//*[@id=\"the-list\"]/tr[3]/td[2]");
	By StatusDropdown = By.xpath("//SPAN[@id='select2-webhook_status-container']");
	By SelectValue = By.xpath("(//TD[@class='forminp'])[2]");
	By getAPIText = By.xpath("//*[@id=\"mainform\"]/nav[1]/a[9]");
	By saveWebhook = By.xpath("//INPUT[@id='publish']");
	String currentUpdatedStatus;
	String currentCreatedStatus;

	public WebhookCheck(WebDriver rotimaticDriver) {
		this.driver = rotimaticDriver;
	}

	public void setUsername(String userName) {
		driver.findElement(username).sendKeys(userName);
	}

	public void setPassword(String passowrd) {
		driver.findElement(password).sendKeys(passowrd);
	}

	public void clickLogInButton() throws InterruptedException {
		driver.findElement(logIn).click();
		Thread.sleep(5000);
	}

	public void clickSettingLink() throws InterruptedException {
		driver.findElement(setting).click();
		Thread.sleep(2000);
	}

	public void clickAPILink() throws InterruptedException {
		WebElement element = driver.findElement(API);
		System.out.println("Webelement" + element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		Thread.sleep(5000);
		driver.findElement(API).click();
		Thread.sleep(2000);
	}

	public void clickWebhooks() throws InterruptedException {
		driver.findElement(webhooks).click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(3000);
	}

	public String getOrderUpdatedStatus() throws InterruptedException {
		currentUpdatedStatus = driver.findElement(Order_Updated_Status).getText();
		Thread.sleep(2000);
		return currentUpdatedStatus;
	}

	public String getOrderCreatedStatus() throws InterruptedException {
		currentCreatedStatus = driver.findElement(Order_Created_Status).getText();
		Thread.sleep(2000);
		return currentCreatedStatus;
	}

	public void clickOrderUpdated() throws InterruptedException {
		driver.findElement(Order_Updated).click();
		Thread.sleep(3000);
	}

	public void clickOrderCreated() throws InterruptedException {
		driver.findElement(Order_Created).click();
		Thread.sleep(2000);
	}

	public void setOrderStatusAsActive() throws InterruptedException {

		WebElement myElement = driver.findElement(StatusDropdown);
		Actions actions = new Actions(driver);
		actions.moveToElement(myElement);
		System.out.println("My Element is" + myElement);
		actions.click();
		Thread.sleep(3000);
		actions.sendKeys(Keys.ARROW_UP);
		actions.sendKeys(Keys.ARROW_UP);
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	public void clickSaveWebhook() throws InterruptedException {
		driver.findElement(saveWebhook).click();
		Thread.sleep(3000);
	}

	public void webhookCheck(String userName, String password) throws InterruptedException {

		this.setUsername(userName);
		System.out.println("Entered Username");

		this.setPassword(password);
		System.out.println("Entered Password");

		this.clickLogInButton();
		System.out.println("Clicked on login button");

		this.clickSettingLink();
		System.out.println("Clicked on setting link");

		this.clickAPILink();
		System.out.println("Clicked on API link");

		this.clickWebhooks();
		System.out.println("Clicked on Webhooks link");

		this.getOrderUpdatedStatus();
		System.out.println("Current updated order status is :" + currentUpdatedStatus);

		this.getOrderCreatedStatus();
		System.out.println("Current created order status is :" + currentCreatedStatus);

	}

	public void testWebhookUpdatedStatus() throws InterruptedException {

		String updatedOrderCurrentStatus = "Disabled";

		if (currentUpdatedStatus.equals(updatedOrderCurrentStatus)) {
			System.out.println("Inside 1st If..!!!");
			this.clickOrderUpdated();
			System.out.println("Clicked on Order Updated Link");
			this.setOrderStatusAsActive();
			this.clickSaveWebhook();
			this.clickWebhooks();
			this.getOrderUpdatedStatus();
			System.out.println("Order Updated Status changed to :"+currentUpdatedStatus);
			Assert.assertEquals(currentUpdatedStatus, "Active");
		} else {
			System.out.println("Everything is Fine");
		}
	}

	public void testWebhookCreatedStatus() throws InterruptedException {

		String createdOrderCurrentStatus = "Disabled";
		
		if (currentCreatedStatus.equals(createdOrderCurrentStatus)) {
			System.out.println("Inside 2nd If..!!!");
			this.clickOrderCreated();
			System.out.println("Clicked on Order Created Link");
			this.setOrderStatusAsActive();
			this.clickSaveWebhook();
			this.clickWebhooks();
			this.getOrderCreatedStatus();
			System.out.println("Order Created Status changed to :"+currentCreatedStatus);
		} else {
			System.out.println("Everything is Fine");
		}
	}
}
