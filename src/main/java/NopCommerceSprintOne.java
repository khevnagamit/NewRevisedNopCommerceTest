import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NopCommerceSprintOne extends Utils{
    LoadProp loadProp = new LoadProp();
    @BeforeMethod
    public void openBrowser()
    {
        System.setProperty("webdriver.chrome.driver","src\\Resourses\\BrowserDrive\\chromedriver.exe");

        //open browser
        driver = new ChromeDriver();

        //set implicitly wait for driver object
        waitingTime();

        //make browser Full Screen
        fullScreen();

        //open the website
        getStringText(loadProp.getProperty("url"));
    }
    @AfterMethod
    public void closing()
    {
        //close window
        closeAllWindowsOpenByDriver();
    }

    @Test(priority = 0)
    public void userRegisterSuccessfully()
    {
        //click on register button
        clickOnElement(By.xpath("//a[@class='ico-register']"));

        //enter first name
        enterText(By.id("FirstName"),loadProp.getProperty("FirstName"));

        //enter last name
        enterText(By.xpath("//input[@name='LastName']"),loadProp.getProperty("Lastname"));

        //select date
        selectByVisibleText(By.name("DateOfBirthDay"),loadProp.getProperty("DOBDay"));

        //select month
        selectByIndexNumber(By.name("DateOfBirthMonth"),6);

        //select year
        selectByValue(By.name("DateOfBirthYear"),loadProp.getProperty("DOBYear"));

        //enter email
        enterText(By.name("Email"),loadProp.getProperty("Email"));
        enterText(By.name("Email"),dateFormateUniqueNumber());
        enterText(By.name("Email"),loadProp.getProperty("Email2"));

        //enter password
        enterText(By.id("Password"),loadProp.getProperty("Password"));

        //enter confirm password
        enterText(By.id("ConfirmPassword"),loadProp.getProperty("ConfirmPassword"));

        //click register button
        clickOnElement(By.id("register-button"));

        //testing Successfully
        String expectedMsg = loadProp.getProperty("ExpectedRegSuccessfullyMsg");
        String actualMsg = findText(By.xpath("//div[@class=\'result\']"));
        Assert.assertEquals(actualMsg,expectedMsg);
    }

    @Test(priority = 1)
    public void RegisterMemberThenSendEmail()
    {
        //click on register button
        clickOnElement(By.xpath("//a[@class='ico-register']"));

        //enter first name
        enterText(By.id("FirstName"),loadProp.getProperty("FirstName"));

        //enter last name
        enterText(By.xpath("//input[@name='LastName']"),loadProp.getProperty("Lastname"));

        //select date
        selectByVisibleText(By.name("DateOfBirthDay"),loadProp.getProperty("DOBDay"));

        //select month
        selectByIndexNumber(By.name("DateOfBirthMonth"),6);

        //select year
        selectByValue(By.name("DateOfBirthYear"),loadProp.getProperty("DOBYear"));

        //enter email
        enterText(By.name("Email"),loadProp.getProperty("Email"));
        enterText(By.name("Email"),dateFormateUniqueNumber());
        enterText(By.name("Email"),loadProp.getProperty("Email2"));

        //enter password
        enterText(By.id("Password"),loadProp.getProperty("Password"));

        //enter confirm password
        enterText(By.id("ConfirmPassword"),loadProp.getProperty("ConfirmPassword"));

        //click register button
        clickOnElement(By.id("register-button"));

        //click on categories ,computers
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]"));

        //click on notebook
        clickOnElement(By.xpath("//h2/a[@href=\"/notebooks\"]"));

        //select Apple MacBook
        clickOnElement(By.xpath("//img[contains(@src,'apple-macbook-pro-13-inch')]"));

        //click email a friend
        clickOnElement(By.xpath("//input[@onclick='setLocation(\"/productemailafriend/4\")']"));

        //enter friend's email
        enterText(By.className("friend-email"),loadProp.getProperty("FriendEmail"));

        //enter message
        enterText(By.xpath("//textarea[@id='PersonalMessage']"),loadProp.getProperty("Message"));

        //click on send email
        clickOnElement(By.name("send-email"));

        //Testing
        String expected =loadProp.getProperty("ExpectedSendMsgSuccessfully");
        String actual = findText(By.xpath("//div[@class=\"result\"]"));
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 2)
    public void userShouldNavigateToCameraPage()
    {
        //click on electronics
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/electronics\"]"));

        //click on camera-photo
        clickOnElement(By.xpath("//h2[@class=\"title\"]//a[@href=\"/camera-photo\"]"));

        //Testing
        String expected = loadProp.getProperty("ExpectedNavigationCameraPage");
        String actual = findText(By.xpath("//h1"));
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 3)
    public  void userShouldAbleToAddTwoProductsToAddToCart() {

        //click on catagories & books
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/books\"]"));

        //select 1 book
        clickOnElement(By.xpath("//h2/a[@href=\"/fahrenheit-451-by-ray-bradbury\"]"));

        //click on add to cart product 1
        clickOnElement(By.id("add-to-cart-button-37"));

        //close msg box
        clickOnElement(By.xpath("//span[@class=\"close\"]"));

        String product1 = findText(By.xpath("//span[@itemprop=\"sku\"]"));

        //books page
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/books\"]"));

        //select 2 book
        clickOnElement(By.xpath("//h2/a[@href=\"/first-prize-pies\"]"));

        //click on add to cart
        clickOnElement(By.id("add-to-cart-button-38"));

        //close msg box
        clickOnElement(By.xpath("//span[@class=\"close\"]"));

        String product2 = findText(By.xpath("//span[@itemprop=\"sku\"]"));

        clickOnElement(By.linkText("Shopping cart"));

        List<WebElement> skuList= getList(By.className("sku-number"));

        List<String> sku_elements=new ArrayList<>();

        for(int i=0; i<skuList.size(); i++){

            //add in string & make array
            sku_elements.add(skuList.get(i).getText());
        }

        //Testing
        String expected[] = {product1,product2};
        String actual[] =sku_elements.toArray(new String[sku_elements.size()]);
        Assert.assertEquals(actual, expected);

    }
    @Test(priority = 4)
    public void userShouldAbleToSelectJewelryByPriceSelection()
    {
        //click on jewelry
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/jewelry\"]"));

        //click on price range $700- $3000
        clickOnElement(By.xpath("//a[@href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]"));

        //making variable name & store in productPrice
        String  productPrice = findText(By.xpath("//span[@class=\"price actual-price\"]"));

        //remove $ from string productPrice
        String pPrice=productPrice.substring(1);

        //converting string to float
        float price =Float.parseFloat(pPrice.replaceAll(",",""));

        //testing
        Assert.assertTrue(price > 700 && price < 3000);

    }

}
