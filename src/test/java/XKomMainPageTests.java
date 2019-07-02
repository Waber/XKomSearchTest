import Pages.XKomMainPageFactory;
import Report.ReportClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class XKomMainPageTests extends ReportClass {

    private WebDriver driver;
    private XKomMainPageFactory factory;


    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.get("https://www.x-kom.pl/");
        factory = new XKomMainPageFactory(driver);
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void testIfHiddenMenuAppearsAfterMouseHover(){
        setTest(getExtent().createTest("Test ukrytego menu"));

        factory.hoverOverMenuElement();
        getLog().info(factory.findCategories());
        Assert.assertTrue(factory.findCategories().contains("Kategorie w dziale"));
    }
}
