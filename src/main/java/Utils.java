import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javax.swing.text.html.CSS.getAttribute;

public class Utils extends BasePage {
    //wait for fix time given in seconds
    public void waitingTime(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //make full screen of web page
    public void fullScreen(){
        driver.manage().window().fullscreen();
    }

    //get text from
    public void getStringText(String text){
        driver.get(text);
    }

    //command to close all windows open by web driver
    public void closeAllWindowsOpenByDriver(){
        driver.quit();
    }

    // click on element
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    //enter text on input field
    public void enterText(By by,String text) {
        driver.findElement(by).sendKeys(text);
    }

    //get text from given Locator
    public String findText(By by){
        String text =driver.findElement(by).getText();
        return text;
    }

    //get attribute from given Locator
    public void gettingAttribute(By by,String attribute) {
        driver.findElement(by).getAttribute(attribute);
    }

    //get css value from Locator
        public String findTextgettingCSSValue(By by,String cssValue) {
            String text = driver.findElement(by).getCssValue(cssValue);
            return text;
        }

    //Select value by visible text from locator
    public static void selectByVisibleText(By by,String text){
        Select select =new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    //select by index number from locator
    public static void selectByIndexNumber(By by,Integer index){
        Select select =new Select(driver.findElement(by));
        select.selectByIndex(index);
    }

    //select by value from locator
    public static void selectByValue(By by ,String value){
        Select select =new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    //waiting time for locator clickable
    public static void waitForClickable(By by,long time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //waiting time for element visible from locator
    public static void  waitForElementVisible(By by,long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //date stamp
    public static String dateFormateUniqueNumber(){
        SimpleDateFormat sdf =new SimpleDateFormat("ddMMyyyyhhmmss");
        Date currDate = new Date();
        return sdf.format(currDate);
    }

    //web element is displaying or not
    public static void elementIsDisplay(By by){
        driver.findElement(by).isDisplayed();
    }

    //clear text from input box
    public static void clearText(By by){
        driver.findElement(by).clear();
    }

    //cleartext  from input box & enter text
    public static void clearAndInputText(By by , String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    //to make list from path
    public List<WebElement> getList(By by){
        return driver.findElements(by);
    }

    //take Screen shot
    public static void takeSnapShot(WebDriver webdriver,String path) throws Exception{
        //convert web driver object to take screenshot
        TakesScreenshot srcShot= ((TakesScreenshot)webdriver);
        //call get screenshot method to create image
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        //move image file to new destination
        File DestFile= new File(path);
        //copy file to destination
        FileUtils.copyFile(srcFile,DestFile);
    }

}
