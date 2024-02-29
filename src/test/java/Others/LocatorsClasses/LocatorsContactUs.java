package Others.LocatorsClasses;

import org.openqa.selenium.By;

public class LocatorsContactUs {
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
}
