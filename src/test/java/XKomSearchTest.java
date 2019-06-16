import Pages.XKomMainPage;
import Report.ReportClass;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class XKomSearchTest extends ReportClass {

   private WebDriver driver;
   private XKomMainPage xFactory;
   private String product = "apple";
    private int siteCount;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        driver.get("https://www.x-kom.pl/");
        xFactory = new XKomMainPage(driver);
        xFactory.productSearch(product);
        //product = JOptionPane.showInputDialog(null,"Podaj nazwę produktu");
    }

    @Test
    public void checkGetIntoProductDetails() throws IOException, InterruptedException {
        //xFactory.printProducts(); //wyświetli listę produktów wyświetlonych na stronie
        setTest(getExtent().createTest("Pierwszy przypadek", "Sprawdzanie szczegółów produktu"));

        driver.switchTo().defaultContent();
        Assert.assertTrue(xFactory.productDetails());
    }

    @Test
    public void checkGoingToNextSite(){
        setTest(getExtent().createTest("Drugi przypadek"));

        if(xFactory.checkForMoreSites()){
            xFactory.nextSite();
        }
        Assert.assertTrue((xFactory.getSiteCount() > 1));
    }

    @Test
    public void checkIfProducerFilterReducePagesAmount() throws InterruptedException {
        setTest(getExtent().createTest("Trzeci test", "Gówno opis"));

        siteCount = xFactory.getSiteCount();
        xFactory.ProducerChekboxCheck();
        Thread.sleep(1000); //w celu odczekania na odświeżenie strony
        Assert.assertTrue(xFactory.getSiteCount() < siteCount);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
