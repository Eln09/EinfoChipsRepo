package Others.LocatorsClasses;

import org.openqa.selenium.By;

public class LocatorsHome {

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
