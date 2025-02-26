package com.google;

import java.util.Date;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSuggestionTask extends BaseClass{
	
	@BeforeClass
	public void beforeClass() {
		LaunchBrowser("chrome");
		impWait(10);
		System.out.println("before class");
	}
	
	@AfterClass
	private void afterClass() {
		//quit();
		System.out.println("After Class");
	}
	
	@BeforeMethod
	private void beforeMethod() {
		Date d = new Date();
		System.out.println("Before Method"+d);
	}
	
	@AfterMethod
	private void afterMethod() {
		Date d = new Date();
		System.out.println("After Method"+d);
	}
	
	@Test
	private void getGoogleSuggestions() {
		urlLaunch("https://www.google.com/");
		WebElement search = driver.findElement(By.xpath("//textarea[@title='Search']"));
		search.sendKeys("places to visit in chennai");
		List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		for(int i=0;i<suggestions.size();i++){			
			System.out.println(suggestions.get(i).getText());			
		}
		int searchPos =0;
		for(WebElement e:suggestions)
		{
			searchPos++;
			if(searchPos ==3) {
				click(e);
				break;
			}
		}
		
		List<WebElement> selectedSearchList = driver.findElements(By.xpath("//div[@class='x3SAYd']//span[@class='OSrXXb']"));
		for(WebElement e:selectedSearchList)
		{
			System.out.println("printing selected text...."+e.getText());
			if(e.getText().endsWith("Marina")) {
				System.out.println("printing only marina beach");
			}
		}

		
       System.out.println("made changes.......");

	}
	

}
