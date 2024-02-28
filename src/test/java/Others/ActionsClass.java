package Others;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.sql.Driver;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ActionsClass {
    public WebDriver driver;

    public ActionsClass(WebDriver driver) {

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
    public void SoftAssertBoolean(boolean x,boolean y,String ifExpected){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
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
    public void SoftAssertString(String x,String y, String ifexpected){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            assertFailed = true;
        } finally {
            if (assertFailed==false) {
                System.out.println(ifexpected);
            }
        }
    }
    public void SoftAssertList(List<String> x,List<String> y, String ifexpected) {
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            assertFailed = true;
        } finally {
            if (assertFailed == false) {
                System.out.println(ifexpected);
            }
        }
    }
    public void SoftAssertInt(int x,int y, String ifexpected){
        SoftAssert softAssert = new SoftAssert();
        boolean assertFailed = false;
        try {
            softAssert.assertEquals(x, y);
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            assertFailed = true;
        } finally {
            if (assertFailed==false) {
                System.out.println(ifexpected);
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
}

