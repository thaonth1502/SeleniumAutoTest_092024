package com.thaonth.keywords;

import com.thaonth.contants.DataConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {
    private static WebDriver driver;

    public WebUI(WebDriver driver){
        this.driver = driver;
    }

    //Assert and Verify
    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        System.out.println("✅ Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        System.out.println("✅ Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        System.out.println("✅ Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        System.out.println("✅ Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }


    //Wait for Element
    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("\uD83D\uDD56 Timeout waiting for the element Visible. " + by.toString());
            logConsole("\uD83D\uDD56 Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("\uD83D\uDD56 Timeout waiting for the element Visible. " + by.toString());
            logConsole("\uD83D\uDD56 Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("❌ Element not exist. " + by.toString());
            logConsole("❌ Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("❌ Element not exist. " + by.toString());
            logConsole("❌ Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DataConfig.EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("\uD83D\uDD56 Timeout waiting for the element ready to click. " + by.toString());
            logConsole("\uD83D\uDD56 Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("\uD83D\uDD56 Timeout waiting for the element ready to click. " + by.toString());
            logConsole("\uD83D\uDD56 Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    //Các hàm xử lý cơ bản
    public static void sleep(double second) {
        try {
            Thread.sleep((long)(1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        waitForElementPresent(by);
        return driver.findElement(by);
    }
    public static List<WebElement> getListWebElement(By by){
        waitForElementPresent(by);
        return driver.findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List< WebElement > listElement = getListWebElement(by);

        if (listElement.size() > 0) {
            System.out.println("✅ Check Element Exist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("❌ Check Element Exist: " + false + " --- " + by);
            return false;
        }
    }

    public static void openURL(String url) {
        driver.get(url);
        sleep(DataConfig.STEP_TIME);
        logConsole("\uD83C\uDF0E Open: " + url);
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        driver.findElement(by).click();
        logConsole("\uD83D\uDFE2 Click element: " + by);
    }

    public static void clickElement(By by, long timeout) {
        waitForElementVisible(by);
        driver.findElement(by).click();
        logConsole("\uD83D\uDFE2 Click element: " + by);
    }

    public static void setText(By by, String value) {
        getWebElement(by).sendKeys(value);
        logConsole("\uD83D\uDD8B Set text: " + value + " on element " + by);
    }

    public static void setKeys(By by, Keys key){
        getWebElement(by).sendKeys(key);
        logConsole("\uD83D\uDD8B Set Keys: " + key + " on element " + by);
    }

    public static void setTextAndKey(By by, String value, Keys key) {
        getWebElement(by).sendKeys(value, key);
        System.out.println("\uD83D\uDD8B Set text: " + value + " on element " + by);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        String text = driver.findElement(by).getText();
        logConsole("➡\uFE0F Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    public static String getElementAttribute(By by, String attributeName){
        waitForElementVisible(by);
        String text = driver.findElement(by).getAttribute(attributeName);
        logConsole("➡\uFE0F Attribute value: " + text);
        return text;
    }

    public static boolean isElementDisplayed(By by){
        return getWebElement(by).isDisplayed();
    }

    public static String getCurrentURL(){
        return driver.getCurrentUrl();
    }


    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    /**
     * Scroll to position element
     * @param element
     * @param position if value is 'true' then scroll top. If value is 'fail' then scroll bottom
     */
    public static void scrollToElement(By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView("+ position +");", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(driver);
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(driver);
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(driver);
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(driver);
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by, String colorName) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid "+ colorName +"'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }



    //Chờ đợi trang load xong mới thao tác
    //Waiting page load finish then take action
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DataConfig.PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition < Boolean > jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("\uD83D\uDC1E FAILED!!! Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition < Boolean > jsLoad = new ExpectedCondition< Boolean >() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("\uD83D\uDC1E FAILED!!! Timeout waiting for page load.");
            }
        }
    }


}
