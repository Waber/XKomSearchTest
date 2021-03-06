package Pages;

import org.openqa.selenium.By;
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


    @FindBy(className = "")
    private WebElement hoverMenu;

    @FindBy(xpath = "//*[@id=\"navigation\"]/ul/li[1]/div/h3")
    private WebElement categoriesText;

    @FindBy(xpath = "")
    private WebElement subcategory;


    public XKomMainPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    public void hoverOverMenuElement(String menuItemClassName){
        hoverMenu = driver.findElement(By.className(menuItemClassName));
        action.moveToElement(hoverMenu).perform();
    }

    public String hoverOverMenus(String menuItemClassName, String subcategory){
        this.subcategory = driver.findElement(By.xpath(subcategory));
        hoverOverMenuElement(menuItemClassName);
        return this.subcategory.getText();
    }

    public String findCategories(){
        return categoriesText.getText();
    }

    public void getIntoCategory(String menuItemClassName, String subcategory ){
        hoverOverMenuElement(menuItemClassName);
        this.subcategory = driver.findElement(By.linkText(subcategory));
        this.subcategory.click();
    }

    public String getCategoryName(String category){
        String categoryName = driver.findElement(By.className(category)).getText();
        return categoryName.replace("\n", " ");
    }

    public String getTitle(){
        return driver.getTitle();
    }


}
