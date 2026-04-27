package com.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Class {
	public static WebDriver driver;


	protected static WebDriver launchBrowser(String browserName) {
	    try {
	        if (browserName.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browserName.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        } else if (browserName.equalsIgnoreCase("firefox")) {
	            driver = new FirefoxDriver();
	        } else {
	            Assert.fail("Invalid browser name: " + browserName);
	        }

	        driver.manage().window().maximize();
	        return driver;

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("ERROR OCCURRED DURING BROWSER LAUNCH: " + e.getMessage());
	        return null;
	    }
	}
	// LAUNCH URL
	protected static void launchUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING URL LAUNCH");
		}
	}

	// GET PAGE TITLE();
	protected static void pageTitle(String title) {
		try {
			title = driver.getTitle();
			System.out.println("Page Title: " + title);
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING GETTING PAGE TITLE");
		}
	}

	// GET CURRENT URL();
	protected static void currentUrl(String url) {
		try {
			url = driver.getCurrentUrl();
			System.out.println("Current URL: " + url);
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING GETTING CURRENT URL");
		}
	}

	// WINDOWS HANDLING
	protected static void windowsHandling(int num) {
		try {
			List<String> allWindow = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(allWindow.get(num));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURED DURING WINDOW HANDLING");
		}
	}

	// GET TEXT()
	protected static void showText(WebElement element) {
		try {
			String text = element.getText();
			System.out.println("Element Text: " + text );
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING GETTING TEXT");
		}
	}

	// GET ATTRIBUTE()
	protected static void showAttribute(WebElement element, String attributeName, String attributeValue) {
		try {
			attributeValue = element.getAttribute(attributeName);
			System.out.println("Attribute[" + attributeName + "] Value: " + attributeValue);
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING ATTRIBUTE FETCH");
		}
	}

	// SEND KEYS(TYPE VALUE)
	protected static void passInput(WebElement element, String value) {
		try {
			element.sendKeys(value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING VALUE PASSING");
		}
	}

	// CLICK BUTTON
	protected static void clickOnElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING ELEMENT CLICK");
		}
	}

	// SELECT OPTION
	protected static void selectOption(WebElement element, String type, String value) {
		Select select = new Select(element);
		try {
			if (type.equalsIgnoreCase("Text")) {
				select.selectByVisibleText(value);
			} else if (type.equalsIgnoreCase("Index")) {
				select.selectByIndex(Integer.parseInt(value)); // Convent Integer into String by using parseInt
			} else if (type.equalsIgnoreCase("Value")) {
				select.selectByValue(value);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING VALUE SELECTION");
		}
	}

	// DESELECT OPTION
	protected static void deselectOption(WebElement element, String type, String value) {
		Select select = new Select(element);
		try {
			if (type.equalsIgnoreCase("Text")) {
				select.deselectByVisibleText(value);
			} else if (type.equalsIgnoreCase("Index")) {
				select.deselectByIndex(Integer.parseInt(value)); // Convent Integer into String by using parseInt
			} else if (type.equalsIgnoreCase("Value")) {
				select.deselectByValue(value);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURED DURING VALUE DESELECTION");
		}
	}

	// ISMULTIPLE()
	protected static void isMultiple(WebElement element) {
		try {
			Select select = new Select(element);
			boolean multiple = select.isMultiple();
			System.out.println("Is Multiple Option Selected(true/false) :" + multiple);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURED DURING MULTIPLE CHECK");
		}
	}

	// FIRST SELECTED OPTION
	protected static void firstSelectedOption(WebElement element) {
		try {
			Select select = new Select(element);
			WebElement firstOption = select.getFirstSelectedOption();
			System.out.println("First selected option: " + firstOption.getText());
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING FIRST OPTION FETCH");
		}
	}

	// ALL SELECTED OPTIONS METHOD
	protected static void allSelectedOptions(WebElement element) {
		try {

		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING ALL SELECTED OPTIONS FETCH ");
		}
	}

	// IS ENABLED
	protected static void toCheckEnabled(WebElement element) {
		try {
			boolean status = element.isEnabled();
			System.out.println("Is Element Enabled: " + status);
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING ELEMENT ENABLE CHECK");
		}
	}

	// IS DISPLAYED
	protected static void toCheckDisplayed(WebElement element) {
		try {
			boolean status = element.isDisplayed();
			System.out.println("Is Element Displayed" + status);
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING ELEMENT SELECTED CHECK");
		}
	}

	// Frames Handling
	protected static void switchToFrame(WebElement frameElement, String type, String value) {
		try {
			if (type.equalsIgnoreCase("index")) {
				driver.switchTo().frame(Integer.parseInt(value));
			} else if (type.equalsIgnoreCase("name")) {
				driver.switchTo().frame(value);
			} else if (type.equalsIgnoreCase("Id")) {
				driver.switchTo().frame(value);
			} else if (type.equalsIgnoreCase("element")) {
				frameElement = driver.findElement(By.xpath(value));
			}
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING FRAME SWITCH");
		}
	}

	// Switch to DEFAULT CONTENT();
	protected static void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING FRAME EXIT!!");
		}
	}

	// ALERT HANDLING();
	protected static void handleAlert(String action, String gettext) {
		try {
			if (action.equalsIgnoreCase("accept")) {
				driver.switchTo().alert().accept();
			} else if (action.equalsIgnoreCase("Dismiss")) {
				driver.switchTo().alert().dismiss();
			} else if (action.equalsIgnoreCase("gettext")) {
				gettext = driver.switchTo().alert().getText();
				System.out.println(gettext);
			} else if (action.equalsIgnoreCase("sendkeys")) {
				driver.switchTo().alert().sendKeys(gettext);
			}
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING ALERT HANDLING");
		}
	}

	// WAIT METHODS(EXPLICIT WAIT, IMPLICIT WAIT)
	protected static void explicitWait( WebElement element, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING EXPLICT WAIT");
		}
	}

	protected static void implicitWait(int seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			Assert.fail("ERROR: OCCURED DURING IMPLICIT WAIT");
		}
	}

	// QUIT BROWSER()
	protected static void browserTermination() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING BROWSER TERMINATION");
		}
	}

	// SCREENSHOT()
	protected static void takeScreenshot(String fileName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("C:\\Users\\Win10\\eclipse-workspace\\MavenProject\\Screenshots" + fileName + ".png");
			FileHandler.copy(source, destination);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING SCREENSHOT CAPTURE");
		}
	}

	// JAVASCRIPT EXECUTOR
	protected static void javaScriptExecutor(WebElement element, String action) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			if (action.equalsIgnoreCase("click")) {
				js.executeScript("arguments[0].click();", element);

			} else if (action.equalsIgnoreCase("scrollDown")) {
				js.executeScript("window.scrollBy(0,500)");

			} else if (action.equalsIgnoreCase("scrollUp")) {
				js.executeScript("window.scrollBy(0,-500)");

			} else if (action.equalsIgnoreCase("scrollIntoView")) {
				js.executeScript("arguments[0].scrollIntoView(true);", element);

			} else if (action.equalsIgnoreCase("setValue")) {
				js.executeScript("arguments[0].value='Test';", element);
			}

		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING JAVASCRIPT EXECUTION");
		}
	}

	// ROBOT CLASS
	protected static void robotActions(String action) {
		try {
			Robot rb = new Robot();

			if (action.equalsIgnoreCase("enter")) {
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);

			} else if (action.equalsIgnoreCase("tab")) {
				rb.keyPress(KeyEvent.VK_TAB);
				rb.keyRelease(KeyEvent.VK_TAB);

			} else if (action.equalsIgnoreCase("down")) {
				rb.keyPress(KeyEvent.VK_DOWN);
				rb.keyRelease(KeyEvent.VK_DOWN);

			} else if (action.equalsIgnoreCase("up")) {
				rb.keyPress(KeyEvent.VK_UP);
				rb.keyRelease(KeyEvent.VK_UP);

			} else if (action.equalsIgnoreCase("copy")) {
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_C);
				rb.keyRelease(KeyEvent.VK_C);
				rb.keyRelease(KeyEvent.VK_CONTROL);

			} else if (action.equalsIgnoreCase("paste")) {
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_CONTROL);
			}

		} catch (Exception e) {
			Assert.fail("ERROR : OCCURE DURING ROBOT ACTION");
		}
		driver.quit();
	}

}
