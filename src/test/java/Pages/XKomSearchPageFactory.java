package Pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;




public class XKomSearchPageFactory {

    private WebDriver driver;
    private String productText;
    private final static Logger log = LoggerFactory.getLogger(XKomSearchPageFactory.class);


    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/span[1]/span[1]")
    WebElement siteCount;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[3]/div[4]/div[1]/a[2]/i[1]")
    WebElement nextSiteBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[2]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
    WebElement product;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[1]/div[7]")
    WebElement specification; //specification field in details page of an searched item

    @FindBy(name = "f[manufacturers][357]")
    WebElement producerCheckbox; //filter for first producer

    @FindBy(name = "f[price][from]")
    WebElement priceRangeFrom; //price filter

    @FindBy(name = "f[price][to]")
    WebElement priceRangeTo;

    @FindBy(className = "prices")
    List<WebElement> productPrices; // gather all elements price


    //Main page start elements
    @FindBy(xpath = "//*[@id=\"searchBar\"]/div[1]/div[1]/input")
    WebElement searchField;

    @FindBy(xpath = "//*[@id=\"searchBar\"]/div[1]/div[3]/button")
    WebElement searchBtn;




    public XKomSearchPageFactory(WebDriver driver){
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

    public boolean productDetails(){
        productText = product.getText();
        log.info("productText value: "+productText);

        product.click();
       return driver.getTitle().contains(productText);//zwraca true :) czyli tytuł strony musi zawierać opis kafla przedmiotu
    }

    public boolean checkForMoreSites(){
        return Integer.parseInt(siteCount.getText()) > 1;
    }

    public void nextSite(){
        nextSiteBtn.click();
    }

    public int getSiteCount() {
        return Integer.parseInt(siteCount.getText());
    }


    public void ProducerChekboxCheck(){
        producerCheckbox.click();
    }

    public void setPriceRange(double from, double to){
        priceRangeFrom.sendKeys(String.valueOf(from));
        priceRangeTo.sendKeys(String.valueOf(to));
        log.info("Ustawiono widełki");
    }

    public List<Double> getPricesList(){ //do dokończenia, problem z  parsowaniem
        List<Double> prices = new ArrayList<Double>(0);

        for (int i = 0; i < productPrices.size(); i++){
            prices.add(Double.parseDouble(productPrices.get(i).getText().replace(",",".").replace(" ","").replace("zł","")));
        }

        return prices;
    }


}
