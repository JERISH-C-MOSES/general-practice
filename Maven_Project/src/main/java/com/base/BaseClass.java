package com.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.openqa.selenium.Alert;
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

    protected static WebDriver driver;

    // 1. Browser Launch
    public static WebDriver launchBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING BROWSER LAUNCH");
        }
        return driver;
    }

    // 2. Close
    public static void closeBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING CLOSE");
        }
    }

    // 3. Quit
    public static void quitBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING QUIT");
        }
    }

    // 4. Navigate To
    public static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING NAVIGATION");
        }
    }

    // 5. Back
    public static void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING BACK");
        }
    }

    // 6. Forward
    public static void navigateForward() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING FORWARD");
        }
    }

    // 7. Refresh
    public static void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING REFRESH");
        }
    }

    // 8. Launch URL
    public static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING URL LAUNCH");
        }
    }

    // 9. Alert
    public static void handleAlert(String action) {
        try {
            Alert alert = driver.switchTo().alert();
            if (action.equalsIgnoreCase("accept")) {
                alert.accept();
            } else if (action.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING ALERT HANDLING");
        }
    }

    // 10. Actions
    public static void mouseHover(WebElement element) {
        try {
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING MOUSE HOVER");
        }
    }

    public static void dragAndDrop(WebElement src, WebElement dest) {
        try {
            new Actions(driver).dragAndDrop(src, dest).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING DRAG AND DROP");
        }
    }

    // 11. Frames
    public static void switchToFrame(WebElement frame) {
        try {
            driver.switchTo().frame(frame);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING FRAME SWITCH");
        }
    }

    // 12. Robot
    public static void pressEnter() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING ROBOT ACTION");
        }
    }

    // 13. Window Handles
    public static void switchWindow(int index) {
        try {
            List<String> list = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(list.get(index));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING WINDOW SWITCH");
        }
    }

    // 14. Dropdown
    public static void selectByText(WebElement element, String text) {
        try {
            new Select(element).selectByVisibleText(text);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING DROPDOWN SELECT");
        }
    }

    // 15. Checkbox
    public static void clickCheckBox(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING CHECKBOX CLICK");
        }
    }

    // 16-18. Status Methods
    public static boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING IS ENABLED");
        }
        return false;
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING IS DISPLAYED");
        }
        return false;
    }

    public static boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING IS SELECTED");
        }
        return false;
    }

    // 19. Get Options
    public static List<WebElement> getOptions(WebElement element) {
        try {
            return new Select(element).getOptions();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET OPTIONS");
        }
        return null;
    }

    // 20. Title
    public static String getTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET TITLE");
        }
        return null;
    }

    // 21. URL
    public static String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET URL");
        }
        return null;
    }

    // 22. Get Text
    public static String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET TEXT");
        }
        return null;
    }

    // 23. Get Attribute
    public static String getAttribute(WebElement element, String attr) {
        try {
            return element.getAttribute(attr);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET ATTRIBUTE");
        }
        return null;
    }

    // 24. Wait
    public static void waitImplicit(int seconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING WAIT");
        }
    }

    // 25. Screenshot
    public static void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + fileName + ".png");
            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING SCREENSHOT");
        }
    }

    // 26. Scroll
    public static void scrollDown(int pixel) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0," + pixel + ")");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING SCROLL");
        }
    }

    // 27. SendKeys
    public static void sendKeys(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING SENDKEYS");
        }
    }

    // 28-30 Dropdown extras
    public static WebElement getFirstSelected(WebElement element) {
        try {
            return new Select(element).getFirstSelectedOption();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET FIRST SELECTED");
        }
        return null;
    }

    public static List<WebElement> getAllSelected(WebElement element) {
        try {
            return new Select(element).getAllSelectedOptions();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING GET ALL SELECTED");
        }
        return null;
    }

    public static boolean isMultiple(WebElement element) {
        try {
            return new Select(element).isMultiple();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING IS MULTIPLE");
        }
        return false;
    }

    // 31 Click
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING CLICK");
        }
    }

    // 32 Radio
    public static void selectRadio(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING RADIO BUTTON");
        }
    }

    // 33 JS Click
    public static void jsClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING JS CLICK");
        }
    }
}