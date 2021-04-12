package StepDefinition;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {

	public WebDriver driver;
	public List<WebElement> links;
	public HttpURLConnection huc;

	@When("^user launch the \"([^\"]*)\" browser$")
	public void user_launch_the_browser(String browser) throws Exception {
		if (browser.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
			driver = new ChromeDriver();
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		}else if(browser.toLowerCase().contains("firefox")){
			System.setProperty("webdriver.gecko.driver","Driver/geckodriver.exe");
			driver = new FirefoxDriver();
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		}
		
		driver.manage().window().maximize();
	}

	@Then("^I verify console errors in \"([^\"]*)\" page$")
	public void i_verify_console_errors_in_page(String url) throws Exception {
		driver.get(url);
		LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logs) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}

	@When("^user closes the application$")
	public void user_closes_the_application() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		driver.quit();
	}

	@Then("^I verify page response from \"([^\"]*)\" page$")
	public void verify_response_page(String url) throws Exception {
		huc = (HttpURLConnection) new URL(url).openConnection();
		huc.setRequestMethod("HEAD");
		huc.connect();
		System.out.println(huc.getResponseCode());
	}

	@When("^user Navigate to \"([^\"]*)\"$")
	public void user_Navigate_to(String url) throws Exception {
		driver.get(url);
	}

	@When("^user capturing the links in the page$")
	public void user_capturing_the_links_in_the_page() throws Exception {
		links = driver.findElements(By.tagName("a"));
	}

	@Then("^user verify the links Status$")
	public void user_verify_the_links_Status() throws Exception {
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				int respCode = huc.getResponseCode();
				if (respCode >= 400) {
					System.out.println(url + " is a broken link"+ " Response code "+ respCode);
				} else {
					System.out.println(url + " is a valid link"+ " Response code "+ respCode);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
