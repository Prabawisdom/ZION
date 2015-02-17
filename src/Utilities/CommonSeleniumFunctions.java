package Utilities;

// Owner : Prabakaran




import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CommonSeleniumFunctions {
	public static WebDriver driver;
	public static String currentBrowser="";
	
	/**
	 * Retrive the Step No, Action Description with input data from external excel file, For Ex:Type
	 * StepNo:which step we going to execute
	 * description:what we going to do
	 * inputData:Data we going to type
	 * @param stepNo
	 * @param description
	 * @param inputData
	 */
	public static void getStepNoDescwithInput(int stepNo, String description, String inputData){
		System.out.println("Step-"+stepNo+":"+description+", Input Data:"+inputData);}
	
	/**
	 * Retrive the Step No, Action Description without input data from external excel file, For Ex:Click
	 * StepNo:which step we going to execute
	 * description:what we going to do
	 * @param stepNo
	 * @param description
	 * @param inputData
	 */
		public static void getStepNoDescwithoutInput(int stepNo, String description){
			System.out.println("Step-"+stepNo+":"+description);
		}
		
	
	/**
	 * Message to display
	 * @param message
	 */
	public static void writeConsole(String message){
		System.out.println("INFO: "+message);			
	}
	
	/**
	 * Message to display when we getting fail
	 * @param failmessage
	 */
	public static void writeFailure(String failmessage){
		writeConsole("ERROR: "+failmessage);
		Reporter.log("<b><font size='3' color='Red'><strong>"+failmessage+"</strong></font></b>");
		Assert.fail(failmessage);
	}
	
	/**
	 * Message to display when we getting pass
	 * @param Successmessage
	 */
	public static void writeSuccess(String Successmessage){
		writeConsole("Success: "+Successmessage);
		Reporter.log("<b><font size='3' color='Green'><strong>"+Successmessage+"</strong></font></b>");
	}

	/**
	 * Enum for the Browser type
	 * @author lenovo
	 *
	 */
		 public static enum browserType
		    {
		      FIREFOX,  INTERNETEXPLORER,  CHROME;
		    }
		 
		 /**
		  * To open the browser
		  * @param browser
		  * @param URL
		  */
		 public static void openBrowser(String browser, String URL)
		    {
		      browserType b = browserType.valueOf(browser.toUpperCase().trim());
		      currentBrowser = browser;
		      writeConsole("Opening " + browser + " Browser...");
		      try
		      {
		        switch (b)
		        {
		        case CHROME: 
		          driver = new FirefoxDriver(); break;
		        case FIREFOX: 
		          driver = new InternetExplorerDriver(); break;
		        case INTERNETEXPLORER: 
		          driver = new ChromeDriver();
		        }
		        
		        navigateTo(URL);
		      }
		      catch (TimeoutException e)
		      {
		    	  writeFailure("Page fail to load within in " + getConfigProperty("pageLoadWaitTime") + " seconds");
		      }
		      catch (Exception e)
		      {
		        writeFailure("Browser: Open Failure/Navigation cancelled, please check the application window.");
//		      testStepFailed("Open Browser : " + browser.toUpperCase());
		      }
		    }
		 
		 /**
		  * URL to native
		  * @param url
		  */
		 public static void navigateTo(String url)
		    {
		      try
		      {
		        writeConsole("Navigating to URL : " + url);
		        driver.get(url);
		        writeSuccess("Navigation Successful : " + url);
//		        writeConsole("Navigate to : " + url);
		      }
		      catch (TimeoutException e)
		      {
		        testStepFailed("Page fail to load within in " + pageLoadWaitTime + " seconds");
		      }
		      catch (Exception e)
		      {
		        writeFailure("Browser: Open Failure/Navigation cancelled, please check the application window.");
//		        testStepFailed("Navigate to : " + url);
		      }
		    }

		 /**
		  * Attribute name and value to perform the action
		  * @param attributename
		  * @param attributevalue
		  * @return
		  */
	public WebElement attributeNameValue(String attributename,String attributevalue) {
		WebElement webelement = null;
		if (attributename == null) {
			writeFailure("Attribute Name is Empty or Null");
		}

		//				This Below Condition will Modify OR to Work on Webdriver and also works with Selenium RC
//		if (attributevalue.contains("css=")) {
//			attributevalue = attributevalue.replace("css=", "");
//			if (attributevalue.contains("contains")) {
//				//						writeConsole("Contains ");
//				webelement = (WebElement) ((JavascriptExecutor)driver).executeScript("return $(\""+ attributevalue +"\")[0]");
//				if (webelement == null) {
//					writeFailure("Attribute Value is Not Found");
//				}
//				return webelement;
//
//			}
//
//		}
	if (attributevalue.contains("xpath=")) {
			attributevalue = attributevalue.replace("xpath=", "");
		}
		switch(attributename) {
		case "classname":
			webelement = driver.findElement(By.className(attributevalue));
			break;
		case "cssselector":
			webelement = driver.findElement(By.cssSelector(attributevalue));
			break;
		case "id":
			webelement = driver.findElement(By.id(attributevalue));
			break;
		case "linktext":
			webelement = driver.findElement(By.linkText(attributevalue));
			break;
		case "name":
			webelement = driver.findElement(By.name(attributevalue));
			break;
		case "partiallinktext":
			webelement = driver.findElement(By.partialLinkText(attributevalue));
			break;
		case "tagname":
			webelement = driver.findElement(By.tagName(attributevalue));
			break;
		case "xpath":
			webelement = driver.findElement(By.xpath(attributevalue));
			break;			
		default:
			writeFailure("Invalid Attribute Name : "+attributename);
			break;

		}
		//				writeConsole("["+webelement+"]");
		return webelement;	
	}
	
	/**
	 * To click the WebElement
	 * @param stepNo
	 * @param attributename
	 * @param attributevalue
	 * @param labelName
	 * @param description
	 */
	public void click(int stepNo, String attributename,String attributevalue, String labelName, String description){
		//writeConsole("Click["+attributename+", "+attributevalue+"]");
		getStepNoDescwithoutInput(stepNo,description);
		attributeNameValue(attributename, attributevalue).click();
		writeConsole(labelName+"is Clicked");
	}
}
