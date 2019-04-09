import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class XKomFactory {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"searchBar\"]/div[1]/div[1]/input")
    WebElement searchField;

    @FindBy(xpath = "//*[@id=\"searchBar\"]/div[1]/div[3]/button")
    WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"productListUtilitiesBar\"]/div[4]/div/span/span")
    WebElement searchAmount;

    @FindBy(xpath = "//*[@id=\"productListUtilitiesBar\"]/div[4]/div/a[2]")
    WebElement NextSiteBtn;

    @FindBy(className = "product-item product-impression")
    List<WebElement> Products;



    public XKomFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

public void productSearch(String product){
        searchField.sendKeys(product);
        searchBtn.click();
    }
}
