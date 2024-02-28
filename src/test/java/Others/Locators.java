package Others;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.By;

import javax.swing.plaf.PanelUI;

public class Locators{

    public By LogoHome(){
        By a= By.xpath("(//div[@class=' header-logo fit-logo-img add-header-height  logo-has-sticky']//a/img)[1]");
        return a;
    }
    public By MenuHeaders() {
        By a = By.xpath("(//nav/ul[@data-title='Home'])[1]/li/a[@class='ubermenu-target ubermenu-item-layout-default ubermenu-item-layout-text_only']/span[@class='ubermenu-target-title ubermenu-target-text']");
        return a;
    }
    public By HomeMenuDomains(){
        By a= By.xpath("(//a/span[text()='Domains'])[1]");
        return a;
    }
    public By SemiconductorDomains(){
        By a= By.xpath("(//a[contains(@href, 'semiconductor') and contains(@class, 'ubermenu-target')])[1]");
        return a;
    }
    public By NewsletterSection(){
        By a= By.xpath("(//h2[@class='wp-block-heading'])[2]");
        return a;
    }
    public By BussinessEmail(){
        By a= By.xpath("//input[@name='email']");
        return a;
    }
    public By ContactUsBtn(){
        By a= By.xpath("(//div[@class='headq']/a/span)[2]");
        return a;
    }
    public By ContactUsformText(int x){
        By a= By.xpath("(//form/p/span/input[not(@autocomplete='new-password')])["+x+"]");
        return a;
    }
    public By ContactUsSelect(){
        By a= By.xpath("(//form/p/span/select)");
        return a;
    }
    public By ContactUsSelectOptions(String x){
        By a= By.xpath("//option[@value='"+x+"']");
        return a;
    }
    public By ContactUsComment(){
        By a= By.xpath("//form//textarea");
        return a;
    }
    public By ChampDrivenBTitle(){
        By a= By.xpath("//span[text()='Championing']");
        return a;
    }
    public By ChampioningSpecializations(){
        By a= By.xpath("(//div[@class='page-section-content vc_row-fluid mk-grid'])[2]//div[@class='vc_column-inner ']/div//h5/span/a/span");
        return a;
    }
    public By AerospaceSpecial(String RestOfXpath){
        By a= By.xpath("(//div[@class='page-section-content vc_row-fluid mk-grid'])[2]//a[@title='Aerospace']"+RestOfXpath);
        return a;
    }
}
