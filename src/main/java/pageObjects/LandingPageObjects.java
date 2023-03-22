package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class LandingPageObjects {
    @FindBy(className ="navigation-menu__list")
    protected WebElement navigationMenu;
    @FindBy(className ="navigation-menu__submenu")
    protected WebElement servicesMenu;
    protected WebDriver driver;

    public LandingPageObjects(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getMenuOptionsLabelText(){
        List<String> labels = new ArrayList<>();
        List<WebElement> menuOptionsLabels = navigationMenu
                .findElements(By.className("navigation-menu__label"));
        for(WebElement element : menuOptionsLabels){
            labels.add(element.getText());
        }
        return labels;
    }
    public List<String> getServicesMenuOptionsLabelText(){
        List<String> labels = new ArrayList<>();
        Actions action = new Actions(driver);
        action.moveToElement(servicesMenu).perform();
        List<WebElement> menuOptionsLabels = servicesMenu
                .findElements(By.className("navigation-menu__submenu-label"));
        for(WebElement element : menuOptionsLabels){
            labels.add(element.getText());
        }
        return labels;
    }

    public void clickOnMenuOption(String option){
        driver.findElement(By.linkText(option)).click();
    }
}
