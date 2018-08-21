package com.rotimaticWoo;

import java.util.concurrent.TimeUnit;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebhookCheckTest {

	WebDriver driver;
	String baseUrl = "https://dev3.rotimatic.com/wp-login.php?redirect_to=https%3A%2F%2Fdev3.rotimatic.com%2Fwp-admin%2Fpost.php%3Fpost%3D246481%26action%3Dedit&reauth=1";
	WebhookCheck objectWebhookCheck;
	String username = "gaurav.mothekadam@cuelogic.co.in";
	String password = "gaurav.mothekadam";

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\\\Gaurav\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void test_webhookCheck() throws InterruptedException {
		
		objectWebhookCheck = new WebhookCheck(driver);
		objectWebhookCheck.webhookCheck(username, password);
		objectWebhookCheck.testWebhookStatus1();
		objectWebhookCheck.testWebhookStatus2();
	}
	
	/*@After
	public void quit() {
		driver.close();
	}*/

}
