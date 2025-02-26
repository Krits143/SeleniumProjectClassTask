package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	
public static WebDriver driver;
	
	public static WebDriver LaunchBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else {
		 //WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver(); 
		}
		return driver;

	}
	
	public static WebDriver browserLaunch(String browser) {
		switch(browser) {
		case "chrome":
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge" :
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "gecko":
			 //WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver(); 	
			
		}
		
		return driver;
	}
	
	public static void urlLaunch(String url) {
		driver.get(url);		
		driver.manage().window().maximize();
	}
	
	public static void impWait(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
	public static void click(WebElement e) {
		e.click();
	}
	
	
	public static String getAttribute(WebElement e) {		
		String att = e.getAttribute("value");
		System.out.println(att);
		return att;		
	}
	
	public static String getText(WebElement e) {		
		String text = e.getText();
		System.out.println(text);
		return text;		
	}
	
	public static String currentURL() {		
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		return currentUrl;		
	}
	
	public static void quit() {
		driver.quit();
	}
	
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	public static void sendKeys(WebElement e,String text) {
		e.sendKeys(text);
	}
	
	
	//Actions
	
	public static void moveToElement(WebElement e) {
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();
	}
	
	//dragAndDrop
	
	public static void dragAndDrop(WebElement src,WebElement des) {
		Actions a = new Actions(driver);
		a.dragAndDrop(src, des).perform();
	}
	
	
	public static void doubleClick(WebElement e) {
		Actions a = new Actions(driver);
		a.doubleClick(e).perform();
	}
	
	public static void rightClick(WebElement e) {
		Actions a = new Actions(driver);
		a.contextClick(e).perform();
	}
	
	//Robot class
	
	public static void keyPressDown() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
	}
	
	public static void keyReleaseDown() throws AWTException {
		Robot r = new Robot();
		r.keyRelease(KeyEvent.VK_DOWN);
	}
	
	
	public static void keyPressEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}
	
	public static void keyReleaseEnter() throws AWTException {
		Robot r = new Robot();
		r.keyRelease(KeyEvent.VK_ENTER);
	}			
	
	public static void selectByIndex(WebElement e,int index) {
		Select s = new Select(e);
		s.selectByIndex(index);
	}
		
	public static void deSelectByIndex(WebElement e,int index) {
		Select s = new Select(e);
		s.deselectByIndex(index);
	}
		
	public static void selectByValue(WebElement e,String value) {
		Select s = new Select(e);
		s.selectByValue(value);
	}
	
	public static void deSelectByValue(WebElement e,String value) {
		Select s = new Select(e);
		s.deselectByValue(value);
	}
	
	public static void selectByVisibleText(WebElement e,String text) {
		Select s = new Select(e);
		s.selectByVisibleText(text);
	}
	
	public static void deSelectByVisibleText(WebElement e,String text) {
		Select s = new Select(e);
		s.deselectByVisibleText(text);
	}
	
	public static void jsSetAttribute(WebElement e,String value) {
		JavascriptExecutor jsexec = (JavascriptExecutor)driver;
		jsexec.executeScript("arguments[0].setAttribute('value','"+value+"')", e);		
	}
	
	public static String jsGetAttribute(WebElement e) {
		JavascriptExecutor jsexec = (JavascriptExecutor)driver;
		Object es = jsexec.executeScript("return arguments[0].getAttribute('value')" , e);
		return es.toString();
			
	}
	
	public static void captureScreenShot(String path) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);	
		System.out.println("printing source........."+src);
		LocalDateTime localTime = LocalDateTime.now();
		System.out.println(localTime);
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm_ss");
		String format = localTime.format(pattern);
		path = path+format+".png";
		FileUtils.copyFile(src, new File(path));
	}
	
	public static void getCurrentWindow() {
		
		Set<String> allId = driver.getWindowHandles();//10,20
		System.out.println(allId);
		
		List<String> li = new LinkedList<>();
		                      
		li.addAll(allId); 
		
		/*for(int i=0;i<li.size();i++) {
			System.out.println("getting the values from list.........."+li.get(i));
		}*/
			
		driver.switchTo().window(li.get(1));
		
	}

}
