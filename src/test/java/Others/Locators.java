package Others;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import javax.swing.plaf.PanelUI;

public class Locators{

    public By LogoHome(){
        return By.xpath("(//div[@class=' header-logo fit-logo-img add-header-height  logo-has-sticky']//a/img)[1]");
    }
    public By MenuHeaders() {
        return By.xpath("(//nav/ul[@data-title='Home'])[1]/li/a[@class='ubermenu-target ubermenu-item-layout-default ubermenu-item-layout-text_only']/span[@class='ubermenu-target-title ubermenu-target-text']");
    }
    public By HomeMenuDomains(){
        return By.xpath("(//a/span[text()='Domains'])[1]");
    }
    public By SemiconductorDomains(){
        return By.xpath("(//a[contains(@href, 'semiconductor') and contains(@class, 'ubermenu-target')])[1]");
    }
    public By NewsletterSection(){
        return By.xpath("(//h2[@class='wp-block-heading'])[2]");
    }
    public By BussinessEmail(){
        return By.xpath("//input[@name='email']");
    }
    public By ContactUsSocialMedia(){
        return By.xpath("(//div[@class='theme-content no-padding'])[3]");
    }
    public By ContactUsBtn(){
        return By.xpath("(//div[@class='headq']/a/span)[2]");
    }
    public By ContactUsformText(int x){
        return By.xpath("(//form/p/span/input[not(@autocomplete='new-password')])["+x+"]");
    }
    public By ContactUsSelect(){
        return By.xpath("(//form/p/span/select)");
    }
    public By ContactUsSelectOptions(String x){
        return By.xpath("//option[@value='"+x+"']");
    }
    public By ContactUsComment(){
        return By.xpath("//form//textarea");
    }
    public By ChampDrivenBussinessSection(){
        return By.xpath("(//div[@class='mk-padding-divider   clearfix'])[2]");
    }
    public By ChampioningSpecializations(){
        return By.xpath("(//div[@class='page-section-content vc_row-fluid mk-grid'])[2]//div[@class='vc_column-inner ']/div//h5/span/a/span");
    }
    public By AerospaceSpecial(String RestOfXpath){
        return By.xpath("(//div[@class='page-section-content vc_row-fluid mk-grid'])[2]//a[@title='Aerospace']"+RestOfXpath);
    }
}
