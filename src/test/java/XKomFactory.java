import org.openqa.selenium.By;
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

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/span[1]/span[1]")
    WebElement siteCount;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/a[2]/i[1]")
    WebElement nextSiteBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
    WebElement product;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[1]/div[7]")
    WebElement specification;





    public XKomFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void productSearch(String product){
        searchField.sendKeys(product);
        searchBtn.click();
    }

    public void printProducts(){

        List<WebElement> products = driver.findElements(By.className("name"));
        for (int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).getAttribute("title"));
        }
    }

    public void productDetails(){
        product.click();
        System.out.println("\n" + specification.getText());
    }

    public boolean checkForMoreSites(){
       if(Integer.parseInt(siteCount.getText()) > 1){
           return true;
       }
       return false;
    }

    public void nextSite(){
        nextSiteBtn.click();
    }
}
