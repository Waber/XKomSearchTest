import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class XKomTest {

   private WebDriver driver;
   private XKomMainPage xFactory;
   private String product = "apple";


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        driver.get("https://www.x-kom.pl/");
        xFactory = new XKomMainPage(driver);
        //product = JOptionPane.showInputDialog(null,"Podaj nazwę produktu");
    }

    @Test
    public void checkGetIntoProductDetails() throws IOException, InterruptedException {
        xFactory.productSearch(product);
        //xFactory.printProducts();
/*
        if(xFactory.checkForMoreSites()){
            xFactory.nextSite();
        }
        Thread.sleep(500);
  */
        driver.switchTo().defaultContent();
        Assert.assertTrue(xFactory.productDetails());
    }

    @Test
    public void checkGoingToNextSite(){
        xFactory.productSearch(product);
        if(xFactory.checkForMoreSites()){
            xFactory.nextSite();
        }
        Assert.assertTrue((xFactory.getSiteCount() > 1));
    }





    @AfterTest
    public void cleanUp(){
        driver.close();
    }
}
