import Pages.XKomSearchPageFactory;
import utilities.ReportClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class XKomSearchTest extends ReportClass {


   private WebDriver driver;
   private XKomSearchPageFactory xFactory;
   private String product = "apple";
    private int siteCount;
    private final static Logger log = LoggerFactory.getLogger("XKomSearchTest");


    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.get("https://www.x-kom.pl/");
        xFactory = new XKomSearchPageFactory(driver);
        xFactory.productSearch(product);
        //product = JOptionPane.showInputDialog(null,"Podaj nazwę produktu");
        log.info("SetUP");
        getLog().info("setUp z klasy bazowej");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void checkGetIntoProductDetails() {
        //xFactory.printProducts(); //wyświetli listę produktów wyświetlonych na stronie
        setTest(getExtent().createTest("Pierwszy przypadek", "Sprawdzanie szczegółów produktu"));

        driver.switchTo().defaultContent();
        Assert.assertTrue(xFactory.productDetails());
    }

    @Test
    public void checkGoingToNextSite(){
        setTest(getExtent().createTest("Sprawdzanie przechodzenia do następnej strony"));

        if(xFactory.checkForMoreSites()){
            xFactory.nextSite();
        }
        Assert.assertTrue((xFactory.getSiteCount() > 1));
    }

    @Test
    public void checkIfProducerFilterReducePagesAmount() throws InterruptedException {
        setTest(getExtent().createTest("Trzeci test", "Sprawdzenie filtra producenta"));

        siteCount = xFactory.getSiteCount();
        xFactory.ProducerChekboxCheck();
        Thread.sleep(1000); //w celu odczekania na odświeżenie strony
        Assert.assertTrue(xFactory.getSiteCount() < siteCount);
    }

    @Test
    public void checkPriceFilter() throws InterruptedException {
        setTest(getExtent().createTest("Czwarty przypadek", "Sprawdzenie filtra finansowego"));

        double from = 4000.00;
        double to = 6000.00;
        boolean priceWithinRange = true;

        xFactory.setPriceRange(from, to);
        Thread.sleep(3000);

        List<Double> priceList = xFactory.getPricesList();

        for (double pr : priceList){
            if(pr < from && pr > to){
                priceWithinRange = false;
            }
        }

        Assert.assertTrue(priceWithinRange);
    }


}
