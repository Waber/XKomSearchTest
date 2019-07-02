package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XKomMainPageFactory {

    private WebDriver driver;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Actions action;

    @FindBy(className = "nav-item-1")
    private WebElement hoverMenu;

    @FindBy(xpath = "//*[@id=\"navigation\"]/ul/li[1]/div/h3")
    private WebElement categoriesText;

    public XKomMainPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    public void hoverOverMenuElement(){

        action.moveToElement(hoverMenu).perform();
    }

    public String findCategories(){
        return categoriesText.getText();
    }


}
