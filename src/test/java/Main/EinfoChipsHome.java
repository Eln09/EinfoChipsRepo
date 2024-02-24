package Main;

import Others.ActionsClass;
import Others.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EinfoChipsHome {

    WebDriver driver=null;
    ActionsClass action;
    Locators locator;

  // 1. Launch https://www.einfochips.com/
    @Parameters("url")

    @BeforeClass
    public void launchBrowser(String url){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        this.action = new ActionsClass(this.driver);
        this.locator= new Locators();

    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    //2. Validate Logo.
    @Test(priority = 1)
    public void validateLogo()throws Exception{
      Thread.sleep(2000);
      action.SoftAssertBoolean(action.elem_present(action.find_elem(locator.LogoHome())),true,"Logo of Einfo Chips founded!");
    }

    //3. First store all Header menus as Services , IPS Frameworks , read this during runtime and validate it in Xpath.
    @Parameters("listMenu")
    @Test(priority = 2)
    public void validateMenuHeaders(String listmenu){

        String[] menuItems = listmenu.split(",");
        List<String> expectedList = Arrays.asList(menuItems);

        List<WebElement> menuElements = action.find_elemList(locator.MenuHeaders());
        List<String> myList = new ArrayList<>();

        //extract text from menu on home page and add it to list
        for (WebElement element : menuElements) {
            String text = element.getText().replace("\n", " ");
            myList.add(text);
        }
      //verify that elements match the parametrized list
       action.SoftAssertList(myList,expectedList,"Expected Home menu elements founded!");
    }

        //4. Mouse hover on Domains from Primary header menu option then click on Semiconductor.
        @Test(priority = 3)
        public void domainsOptions() throws Exception{
            Thread.sleep(2000);
            action.MouseHover(action.find_elem(locator.HomeMenuDomains()));
            Thread.sleep(2000);
            WebElement semiconductorOption= action.find_elem(locator.SemiconductorDomains());
            if(action.elem_present(semiconductorOption)) {
                action.click_elem(semiconductorOption);
                Thread.sleep(2000);
            }else {
                System.out.println("Semiconductor option not in Domains menu");
            }
        }

        //5. Validate in Semiconductor page UTL = https://www.einfochips.com/domains/semiconductor/ and Title  = Semiconductor Design Services | ASIC/FPGA Design Services
        @Parameters({"SemiCondUrl","SemiCondTitle"})
        @Test(priority = 4)
        public void validateSemiCond_page(String SemiCondUrl, String SemiCondTitle) throws Exception{

            Thread.sleep(3000);
            String SemiCpageUrl = driver.getCurrentUrl();
            String SemiCpageTitle = driver.getTitle();

            action.SoftAssertString(SemiCpageUrl, SemiCondUrl, "URL not expected: " + SemiCpageUrl);
            action.SoftAssertString(SemiCpageTitle, SemiCondTitle, "title not expected: " + SemiCondTitle);
            System.out.println("The semiconductor page url and title are the expected ones:" +
                    "\n     - url: "+SemiCpageUrl+"" +
                    "\n     - title: "+SemiCpageTitle);
        }

        //6. Click on the logo , application should move back to Home Page.
        @Test(priority = 5)
        public void backHome_page() throws Exception{
            //action.click_elem(action.find_elem(locator.Homebtn()));
            Thread.sleep(2000);
            action.JsExecutor("arguments[0].click();", action.find_elem(locator.LogoHome()));
            Thread.sleep(2000);
        }

    //7. Do scroll to element of Subscribe to Newsletter section and validate the text box is having placeholder value as 'Business Email'
    @Parameters("NewsletterEmail")
    @Test(priority = 6)
    public void validateNewsletter_section(String NewsletterEmail)throws Exception{
        action.JsExecutor("arguments[0].scrollIntoView();", action.find_elem(locator.NewsletterSection()));
        Thread.sleep(2000);
        String Emailbox= action.find_elem(locator.BussinessEmail()).getAttribute("placeholder");
        action.SoftAssertString(Emailbox,NewsletterEmail, "The value of the Newsletter textbox is the expected one!");
    }

    //8. In Footer section click on Contact US button , page will open in a new TAB. Just Fill the forms , but don't submit it as it is Production Environment.
    //9. Close the contact us TAB.
    @Parameters({"ContactName","ContactEmail","ContactCell","ContactCompany","ContactOption","ContactComment"})
    @Test(priority = 7)
    public void ContactUs_page(String Name,String Email,String Cel,String Compa,String Opt,String Com)throws Exception{
        action.click_elem(action.find_elem(locator.ContactUsBtn()));
        Thread.sleep(2000);
        action.handleTab(false);

        //action.JsExecutor("arguments[0].scrollIntoView();", action.find_elem(By.id("contacttab")));
        action.find_elem(locator.ContactUsformText(1)).sendKeys(Name);
        action.find_elem(locator.ContactUsformText(2)).sendKeys(Email);
        action.find_elem(locator.ContactUsformText(3)).sendKeys(Cel);
        action.find_elem(locator.ContactUsformText(4)).sendKeys(Compa);
        action.click_elem(action.find_elem(locator.ContactUsSelect()));
        Thread.sleep(1000);
        action.click_elem(action.find_elem(locator.ContactUsSelectOptions(Opt)));
        action.find_elem(locator.ContactUsComment()).sendKeys(Com);
        Thread.sleep(5000);
        driver.close();

    }

    //10. In Middle of Home page validate Championing Innovation Driven Business option has total 10 specialization.
    @Parameters("EspecializationNum")
    @Test(priority = 8)
    public void validateChampionInnovation(int EspNum)throws Exception {
        action.handleTab(true);
        Thread.sleep(2000);
        action.JsExecutor("arguments[0].scrollIntoView();", action.find_elem(locator.ChampDrivenBTitle()));
        int count= action.find_elemList(locator.ChampioningSpecializations()).size();
        action.SoftAssertInt(count,EspNum,"Championing Innovation Driven Business option has a total of 10 specialization.");
        Thread.sleep(4000);
    }

    //11. Validate if Section is 'Aerospace' then its corresponding image's src contains text as 'aerospace'.
    @Test(priority = 9)
    public void validateAerospace_section(){
        WebElement aerospace= action.find_elem(locator.AerospaceSpecial(""));
        action.SoftAssertBoolean(action.elem_present(aerospace),true,"Aerospace Specialization founded!");

        String AerospaceImg= action.find_elem(locator.AerospaceSpecial("//img")).getAttribute("src");
        boolean HasAerospaceSRC = AerospaceImg.toLowerCase().contains("aerospace");
        action.SoftAssertBoolean(HasAerospaceSRC,true,"Aerospace image's src contains text 'aerospace'");
        //System.out.println(myList.get(0));
        //action.FinalSoftAssert();
    }

}