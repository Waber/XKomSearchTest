package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ReportClass {

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test; //Zmienne statyczne żeby każda klasa generująca raporty używała tych samych zmiennych (generowanie jednego raportu)

    @BeforeSuite
    public void reportSetUp(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "Przykładowe");
        extent.setSystemInfo("User Name", "PW");

        htmlReporter.config().setDocumentTitle("Raport testów");
        htmlReporter.config().setReportName("Testy witryny X-kom");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

    }

    @AfterMethod
    public void getResult(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "FAILED", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "PASSED", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    @AfterSuite
    public void reportAfterTestSuite(){
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static ExtentHtmlReporter getHtmlReporter() {
        return htmlReporter;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void setTest(ExtentTest test) {
        ReportClass.test = test;
    }
}
