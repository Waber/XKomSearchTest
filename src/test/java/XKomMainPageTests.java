import Pages.XKomMainPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import utilities.ExcelUtil;
import utilities.ReportClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class XKomMainPageTests extends ReportClass {

    private WebDriver driver;
    private XKomMainPageFactory factory;
    private ExcelUtil excel;
    private String menuItem;
    private String subcategory;


    @BeforeClass
    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //zastęouje System.setProperty
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        factory = new XKomMainPageFactory(driver);
        excel = new ExcelUtil();
        excel.setExcelFileSheet("XKomData.xlsx", "hiddenMenu");
    }

    @BeforeMethod
    public void beforeEachMethod(){
        driver.get("https://www.x-kom.pl/");
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void testIfHiddenMenuAppearsAfterMouseHover(){
        setTest(getExtent().createTest("Test ukrytego menu"));

        factory.hoverOverMenuElement("nav-item-1");
        assertThat(factory.findCategories().contains("Kategorie w dziale"));
    }

    @Test
    public void validateAllHiddenMenu(){
        setTest(getExtent().createTest("Test ukrytych menu (dane z pliku)"));


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

    @Test
    public void checkRedirectingToProperSiteFromHiddenMenu(){
        setTest(getExtent().createTest("Sprawdzanie przekierowania do odpowiedniej strony z ukrytego menu"));
        String categorySiteTitle;
        for(int i = 1; i <= excel.rowCount(); i++){
            menuItem = excel.getCellData(i, 0);
            for(int j = 3; j < excel.columnCount(i); j++){
                subcategory = excel.getCellData(i, j);
                factory.getIntoCategory(menuItem,subcategory);
                getLog().info(String.valueOf("ilość kolumn " + excel.columnCount(i)));
                if(subcategory.equals("Pokaż wszystkie")){
                    subcategory = factory.getCategoryName(menuItem); //pomoc do sprawdzenia ostatniej strony w danej kategorii
                }
                categorySiteTitle = factory.getTitle();
                assertThat(categorySiteTitle).contains(subcategory);
            }
        }
    }
}
