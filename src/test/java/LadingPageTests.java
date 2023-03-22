import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LandingPageObjects;


public class LadingPageTests {
    String baseUrl = "https://dev-bsa-dev-test.pantheonsite.io";
    String[] navigationMenuLabels = {"SERVICES", "EXPERIENCE", "COMPANY", "CAREERS"};
    String[] servicesMenuLabels = {"WELDING", "INSPECTION", "COATING", "PERSONNEL TRAINING",
            "CONSULTING", "PROJECT MANAGEMENT"};
    LandingPageObjects landingPageObjects;
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(description = "Verify the right options are displayed in the navigation menu.")
    public void rightMenuOptionsDisplayed(){
        landingPageObjects = new LandingPageObjects(driver);
        Assert.assertEquals(landingPageObjects.getMenuOptionsLabelText().size(), 4);
        for (int i = 0; i < landingPageObjects.getMenuOptionsLabelText().size(); i++){
            Assert.assertEquals(landingPageObjects.getMenuOptionsLabelText().get(i), navigationMenuLabels[i]);
        }
    }
    @Test(description = "Verify the right options are displayed in the services dropdown.")
    public void rightServicesMenuOptionsDisplayed(){
        landingPageObjects = new LandingPageObjects(driver);
        Assert.assertEquals(landingPageObjects.getServicesMenuOptionsLabelText().size(), 6);
        for (int i = 0; i < landingPageObjects.getServicesMenuOptionsLabelText().size(); i++){
            Assert.assertEquals(landingPageObjects.getServicesMenuOptionsLabelText().get(i), servicesMenuLabels[i]);
        }
    }

    @Test(description = "Verify the user is redirected to the company culture section" +
            " when the Company menu option is clicked .")
    public void clickingOnCompanyMenuOptionRedirectsToCompanyCultureSection(){
        landingPageObjects = new LandingPageObjects(driver);
        landingPageObjects.clickOnMenuOption("COMPANY");
        Assert.assertTrue(driver.getCurrentUrl().contains("/#culture"));
    }

    @Test(description = "Verify the user is redirected to the openings section" +
            " when the Careers menu option is clicked .")
    public void clickingOnCareersMenuOptionRedirectsToOpeningsSection(){
        landingPageObjects = new LandingPageObjects(driver);
        landingPageObjects.clickOnMenuOption("CAREERS");
        Assert.assertTrue(driver.getCurrentUrl().contains("/#openings"));
    }
}
