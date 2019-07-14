import Pages.XKomMainPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtil;
import utilities.ReportClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class XKomMainPageTests extends ReportClass {

    private WebDriver driver;
    private XKomMainPageFactory factory;
    private ExcelUtil excel;
    private String menuItem;
    private String subcategory;


    @BeforeMethod
    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //zastęouje System.setProperty
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
        assertThat(factory.findCategories().contains("Kategorie w dziale"));
    }

    @Test
    public void validateAllHiddenMenu(){//dopisać raporty
        setTest(getExtent().createTest("Test ukrytych menu (dane z pliku)"));

        excel = new ExcelUtil();
        excel.setExcelFileSheet("XKomData.xlsx", "hiddenMenu");
        int countContains = 0;

        for(int i = 1; i <= excel.rowCount(); i++){
            menuItem = excel.getCellData(i,0);
            subcategory = excel.getCellData(i,1);
            if(factory.hoverOverMenus(menuItem, subcategory).contains(excel.getCellData(i,2))){
                countContains++;
            }
        }
        assertThat(countContains).isEqualTo(excel.rowCount());
    }
}
