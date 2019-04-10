import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class XKomTest {

    WebDriver driver;
    XKomFactory xFactory;
    String product;


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        driver.get("https://www.x-kom.pl/");
        product = JOptionPane.showInputDialog(null,"Podaj nazwę produktu");
    }

    @Test
    public void searchForSth() throws IOException, InterruptedException {
        xFactory = new XKomFactory(driver);
        xFactory.productSearch(product);
        xFactory.printProducts();

        if(xFactory.checkForMoreSites()){
            xFactory.nextSite();
        }
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        xFactory.productDetails();
    }



    @AfterTest
    public void cleanUp(){
        driver.close();
    }
}
