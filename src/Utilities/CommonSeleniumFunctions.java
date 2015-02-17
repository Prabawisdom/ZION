package Utilities;

// Owner : Prabakaran

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class CommonSeleniumFunctions {
	WebDriver driver;
	
	public static void writeConsole(String message){
		System.out.println("INFO: "+message);			
	}
	
	public static void writeFailure(String failmessage){
		writeConsole("ERROR: "+failmessage);
		Reporter.log("<b><font size='3' color='Red'><strong>"+failmessage+"</strong></font></b>");
		Assert.fail(failmessage);
	}

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
}
