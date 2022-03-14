import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LoginPageTest extends BaseClass {

    private Logger log = Logger.getLogger(Log.class.getName());
    private WebDriver driver;
    private Properties p;

@BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        p = getObjRepo();

    }

    @Test

    public void usingRightCredentials() {
        driver.get(p.get("url").toString());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement uName = driver.findElement(By.xpath(p.get("username").toString()));
        uName.sendKeys(p.get("uname_val").toString());
        WebElement uPassWord = driver.findElement(By.xpath(p.get("password").toString()));
        uPassWord.sendKeys(p.get("upass_val").toString());
        WebElement logInButton = driver.findElement(By.xpath(p.get("login_btn").toString()));
        logInButton.click();
        WebElement welcomeMessage = driver.findElement(By.xpath("//h1[@class='h2 m-b-2']"));
        Assert.assertTrue(welcomeMessage.isDisplayed());
    }
@Test
    public void usingWrongPassword(){
        driver.get(p.get("url").toString());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement uName = driver.findElement(By.xpath(p.get("username").toString()));
        uName.sendKeys(p.get("uname_val").toString());
        WebElement uPassWord = driver.findElement(By.xpath(p.get("password").toString()));
        uPassWord.sendKeys(p.get("incorrectupass").toString());
        WebElement logInButton = driver.findElement(By.xpath(p.get("login_btn").toString()));
        logInButton.click();
        WebElement loginErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger animated fadeIn']"));
        Assert.assertTrue(loginErrorMessage.isDisplayed());
    }
    @Test
    public void usingWrongEmail(){
        driver.get(p.get("url").toString());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement uName = driver.findElement(By.xpath(p.get("username").toString()));
        uName.sendKeys(p.get("incorrect_email").toString());
        WebElement uPassWord = driver.findElement(By.xpath(p.get("password").toString()));
        uPassWord.sendKeys(p.get("upass_val").toString());
        WebElement logInButton = driver.findElement(By.xpath(p.get("login_btn").toString()));
        logInButton.click();
        WebElement loginErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger animated fadeIn']"));
        Assert.assertTrue(loginErrorMessage.isDisplayed());
    }

    @Test
    public void usingWrongEmailAndWrongPassword(){
        driver.get(p.get("url").toString());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement uName = driver.findElement(By.xpath(p.get("username").toString()));
        uName.sendKeys(p.get("incorrect_email").toString());
        WebElement uPassWord = driver.findElement(By.xpath(p.get("password").toString()));
        uPassWord.sendKeys(p.get("incorrectupass").toString());
        WebElement logInButton = driver.findElement(By.xpath(p.get("login_btn").toString()));
        logInButton.click();
        WebElement loginErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger animated fadeIn']"));
        Assert.assertTrue(loginErrorMessage.isDisplayed());

    }
    @AfterMethod
    public void tearDown(){
    driver.quit();
    }
}