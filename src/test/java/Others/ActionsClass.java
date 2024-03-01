package Others;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ActionsClass {

    public static final String SSpath= System.getProperty("user.dir")+"/Screenshot";
    private WebDriver driver;

    ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest logger;
    public void ActionsDriver(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement find_elem(By by){
        WebElement a= driver.findElement(by);
        return a;
    }
    public List<WebElement> find_elemList(By by){
        List<WebElement> a= driver.findElements(by);
        return a;
    }
    public boolean elem_present(By by) {
        WebElement a= driver.findElement(by);
        boolean flag = false;

        try {
            if (a.isDisplayed()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
    public void SoftAssertBoolean(boolean x,boolean y,String ifExpected, String ScreenshotName){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
            logger.pass(ifExpected);
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            captureSS(ScreenshotName);
            logger.fail(ScreenshotName);
            assertFailed = true;
        } finally {
            if (assertFailed==false) {
                System.out.println(ifExpected);
            }
        }
    }
//.isDisplaayed causes trouble in jenkins
    public void click_elem(By by, String ifSuccess) {
        WebElement a= driver.findElement(by);
        try {
            if (a.isDisplayed()) {
                a.click();
                System.out.println(ifSuccess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void MouseHover(By by){
        Actions a = new Actions(driver);
        try{
            WebElement elem = driver.findElement(by);
            a.moveToElement(elem).perform();
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void SoftAssertString(String x,String y, String ifExpected, String ScreenshotName){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
            logger.pass(ifExpected);
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            captureSS(ScreenshotName);
            assertFailed = true;
            logger.fail(ScreenshotName);
        } finally {
            if (assertFailed==false) {
                System.out.println(ifExpected);
            }
        }
    }
    public void SoftAssertList(List<String> x,List<String> y, String ifExpected, String ScreenshotName) {
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
            logger.pass(ifExpected);
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            captureSS(ScreenshotName);
            assertFailed = true;
            logger.fail(ScreenshotName);
        } finally {
            if (assertFailed == false) {
                System.out.println(ifExpected);
            }
        }
    }
    public void SoftAssertInt(int x,int y, String ifExpected, String ScreenshotName){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
            logger.pass(ifExpected);
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            captureSS(ScreenshotName);
            assertFailed = true;
            logger.fail(ScreenshotName);
        } finally {
            if (assertFailed==false) {
                System.out.println(ifExpected);
            }
        }
    }
   /* public void FinalSoftAssert(){
        softAssert.assertAll();
    }*/
    public void JsExecutor(String arguments, By by){
        WebElement a= driver.findElement(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        (jsExecutor).executeScript(arguments,a);
    }
    public void handleTab(Boolean FocusOnHome){
        // Get handles of all windows
        Set<String> windowHandles = driver.getWindowHandles();
        if(FocusOnHome==false){
            // Switch to the new tab
            for (String handle : windowHandles) {
                if (!handle.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        } else{

            driver.switchTo().window((String) windowHandles.toArray()[0]);
        }

    }
    public void captureSS(String SSName){
        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Copy the file to a location and use try-catch block to handle exception
        try {
            FileHandler.copy(screenshot, new File(SSpath+"/"+SSName+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void ExtentReports(){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/einfochips.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "EinfoChips test");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Test Team");
        spark.config().setDocumentTitle("EinfoChips Application QA ");
// Name of the report
        spark.config().setReportName("EinfoChips Selenium testNG ");
// Dark Theme
        spark.config().setTheme(Theme.STANDARD);
    }
    public void EndTests(){
        driver.quit();
        extent.flush();
    }
}

