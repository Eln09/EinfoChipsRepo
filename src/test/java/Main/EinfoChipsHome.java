package Main;

import Others.ActionsClass;
import Others.LocatorsClasses.LocatorsContactUs;
import Others.LocatorsClasses.LocatorsHome;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EinfoChipsHome extends ActionsClass{

    WebDriver driver;
    LocatorsHome locator;
    LocatorsContactUs locatorCU;

    // 1. Launch https://www.einfochips.com/
    @Parameters("url")

    @BeforeClass
    public void launchBrowser(String url) {
        ExtentReports();
        logger = extent.createTest("EinfoChips Home");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        ActionsDriver(driver);
        locator = new LocatorsHome();
        locatorCU = new LocatorsContactUs();
    }

    @AfterClass
    public void closeBrowser(){
        EndTests();
    }

    //2. Validate Logo.
    @Test(priority = 1)
    public void validateLogo()throws Exception{
      Thread.sleep(2000);
      //Assert logo is present then give message, if not, a screenshot will be taken
      SoftAssertBoolean(elem_present(locator.LogoHome()),true,"Logo of Einfo Chips founded!", "EinfoChipsLogo");

    }

    //3. First store all Header menus as Services , IPS Frameworks , read this during runtime and validate it in Xpath.
    @Parameters("listMenu")
    @Test(priority = 2)
    public void validateMenuHeaders(String listmenu){

        String[] menuItems = listmenu.split(",");
        List<String> expectedList = Arrays.asList(menuItems);

        List<WebElement> menuElements = find_elemList(locator.MenuHeaders());
        List<String> myList = new ArrayList<>();

        //extract text from menu on home page and add it to list
        for (WebElement element : menuElements) {
            String text = element.getText().replace("\n", " ");
            myList.add(text);
        }
      //verify that elements match the parametrized list
        SoftAssertList(myList,expectedList,"Expected Home menu elements founded!","HomeMenuElements");
    }

        //4. Mouse hover on Domains from Primary header menu option then click on Semiconductor.
        @Test(priority = 3)
        public void domainsOptions() throws Exception{

            try {
                MouseHover(locator.HomeMenuDomains());
                Thread.sleep(2000);
                click_elem(locator.SemiconductorDomains(),"Semiconductor option from Domains menu founded!");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        //5. Validate in Semiconductor page UTL = https://www.einfochips.com/domains/semiconductor/ and Title  = Semiconductor Design Services | ASIC/FPGA Design Services
        @Parameters({"SemiCondUrl","SemiCondTitle"})
        @Test(priority = 4)
        public void validateSemiCond_page(String SemiCondUrl, String SemiCondTitle) throws Exception{
            logger = extent.createTest("Semiconductor Home");
            Thread.sleep(2000);
            String SemiCpageUrl = driver.getCurrentUrl();
            String SemiCpageTitle = driver.getTitle();

            SoftAssertString(SemiCpageUrl, SemiCondUrl, "URL is expected: " + SemiCpageUrl,"SemiconductorURL" );
            SoftAssertString(SemiCpageTitle, SemiCondTitle, "title is expected: " + SemiCondTitle,"SemiconductorTitle");
        }

        //6. Click on the logo , application should move back to Home Page.
        @Test(priority = 5)
        public void backHome_page() throws Exception{
            //click on logo to go back home with JS executor
            JsExecutor("arguments[0].click();", locator.LogoHome());
            Thread.sleep(2000);
            logger = extent.createTest("Back on EinfoChips Home");
        }

    //7. Do scroll to element of Subscribe to Newsletter section and validate the text box is having placeholder value as 'Business Email'
    @Parameters("NewsletterEmail")
    @Test(priority = 6)
    public void validateNewsletter_section(String NewsletterEmail)throws Exception{
        JsExecutor("arguments[0].scrollIntoView();", locator.NewsletterSection());
        Thread.sleep(2000);
        String Emailbox= find_elem(locator.BussinessEmail()).getAttribute("placeholder");
        //asserting value of Email box is same as indicated in einfochips.xml
        SoftAssertString(Emailbox,NewsletterEmail, "The value of the Newsletter textbox is the expected one!","NewsLetterTextbox");
    }

    //8. In Footer section click on Contact US button , page will open in a new TAB. Just Fill the forms , but don't submit it as it is Production Environment.
    //9. Close the contact us TAB.
    @Parameters({"ContactName","ContactEmail","ContactCell","ContactCompany","ContactOption","ContactComment"})
    @Test(priority = 7)
    public void ContactUs_page(String Name,String Email,String Cel,String Compa,String Opt,String Com)throws Exception{
        click_elem(locatorCU.ContactUsBtn(),"Contact Us button founded!");
        Thread.sleep(2000);
        handleTab(false);

        logger = extent.createTest("EinfoChips Contact Us");
        JsExecutor("arguments[0].scrollIntoView();", locatorCU.ContactUsSocialMedia());
        //fill contact us form with data from einfochips.xml
        find_elem(locatorCU.ContactUsformText(1)).sendKeys(Name);
        find_elem(locatorCU.ContactUsformText(2)).sendKeys(Email);
        find_elem(locatorCU.ContactUsformText(3)).sendKeys(Cel);
        find_elem(locatorCU.ContactUsformText(4)).sendKeys(Compa);
        click_elem(locatorCU.ContactUsSelect(), "Dropdown from Contact Us form founded!");
        Thread.sleep(1000);
        click_elem(locatorCU.ContactUsSelectOptions(Opt),"Domains option selected!");
        find_elem(locatorCU.ContactUsComment()).sendKeys(Com);
        Thread.sleep(5000);
        driver.close();
    }

    //10. In Middle of Home page validate Championing Innovation Driven Business option has total 10 specialization.
    @Parameters("EspecializationNum")
    @Test(priority = 8)
    public void validateChampionInnovation(int EspNum)throws Exception {
        handleTab(true);
        Thread.sleep(2000);
        logger = extent.createTest("EinfoChips Home sections");
        JsExecutor("arguments[0].scrollIntoView();", locator.ChampDrivenBussinessSection());
        int count= find_elemList(locator.ChampioningSpecializations()).size();
        SoftAssertInt(count,EspNum,"Championing Innovation Driven Business option has a total of 10 specialization.","ChampionInnovationSpecializations");
        Thread.sleep(4000);
    }

    //11. Validate if Section is 'Aerospace' then its corresponding image's src contains text as 'aerospace'.
    @Test(priority = 9)
    public void validateAerospace_section(){
        SoftAssertBoolean(elem_present(locator.AerospaceSpecial("")),true,"Aerospace Specialization founded!","AerospaceSpecialization");//check that Aerospace is in section

        String AerospaceImg= find_elem(locator.AerospaceSpecial("//img")).getAttribute("src");//get src from Aerospace image
        boolean HasAerospaceSRC = AerospaceImg.toLowerCase().contains("aerospace");
        SoftAssertBoolean(HasAerospaceSRC,true,"Aerospace image's src contains text 'aerospace'","AerospaceImg");
    }
}
