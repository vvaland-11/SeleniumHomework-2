package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    protected static WebDriver driver;

    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    static String expectedRegistrationCompletionMessage = "Your registration completed";
    static String expectedUserVoteCompletionMessage="Only registered users can vote.";
    static String expectedEmailAFriendCompletionMessage="Only registered customers can use email a friend feature";
    static String expectedProductComparisonCompletionMessage="user can able to compare the product successfully";
    static String expectedSameProductShouldBeInShoppingCart="User Can able to Add the product to the shopping cart";
    static String expectedReferProductMessage="user should be able to send a friend successfully";
    static String expectedAbleToVoteSuccessfullMessage="register user should able to vote successfully";

    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.close();
    }



    @Test
    public static void toVerifyUserShouldBEAbletoRegistration() {
        clickOnElement(By.className(("ico-register")));
        //type first name
        typeText(By.id("FirstName"),"TestFirstName");
        //type LastName
        typeText(By.id("LastName"),"TestLastName");
        //type email id
        typeText(By.name("Email"),"TestMail+"+ timestamp()+"@gmail.com");
        //type password
        typeText(By.id("Password"),"Test1234");
        //re-enter password
        typeText(By.id("Password"),"Test1234");
        //click on register
        clickOnElement(By.id("register-button"));
        //get the written message
        String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println("My Message:" + actualMessage);
        Assert.assertEquals(actualMessage,expectedRegistrationCompletionMessage,"Registration not completed successfully");

    }

    @Test
    public static void ToVerifyUserShouldBeAbleToVoteToCommunityPoll(){
         String getExpectedRegistrationCompletionMessage="Only registered users can vote.";
        //click on good option
        clickOnElement(By.id("pollanswers-2"));
        //click on vote button
        clickOnElement(By.id("vote-poll-1"));
        //get written message
        String actualMessage=getTextFromElement(By.xpath("//div[@id='block-poll-vote-error-1']"));
        System.out.println("My message:"+actualMessage);
        Assert.assertEquals(actualMessage,expectedUserVoteCompletionMessage,"User is able to vote successfully");

    }
    @Test
    public static void toVerifyUserShouldBeAbleToEmailAFriendSuccessfully(){
        //click on Add to cart button on Apple MacBook Pro 13-inch
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']/div[2]/div[2]/div/div[2]/div[3]/div[2]/button[1]"));
        //click on email a friend
        clickOnElement(By.className("email-a-friend"));
        //enter friend's email id
        typeText(By.className("friend-email"),"testemail1234+"+timestamp()+"@gmail.com");
        // enter personal email id
        typeText(By.className("your-email"),"testemail4321+"+timestamp()+"@gamil.com");
        // click on send email button
        clickOnElement(By.name("send-email"));
        //get text message
        String actualMessage = getTextFromElement(By.xpath("//div[@class='message-error validation-summary-errors']"));
        System.out.println("My message:"+ actualMessage);
        Assert.assertEquals(actualMessage,expectedEmailAFriendCompletionMessage,"User can not able to email a friend successfully");
    }

    @Test
    public static void toVerifyUserShouldBeAbleToCompareTheProductSuccessfully(){
        //click on HTC One M8 Android L 5.0 Lollipop
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']/div[2]/div[3]/div/div[2]/div[3]/div[2]/button[2]"));
        //$25 Virtual Gift Card
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']/div[2]/div[4]/div/div[2]/div[3]/div[2]/button[2]"));
        //click on comparison button
        clickOnElement(By.xpath("//div[@id='bar-notification']/div/p/a"));
        //click on clear button
        clickOnElement(By.className("clear-list"));
        //get  a text message
        String actualMessage=getTextFromElement(By.className("no-data"));
        System.out.println("My Message: "+ actualMessage);
        Assert.assertEquals(actualMessage,expectedProductComparisonCompletionMessage,"User can not able compare the product successfully successfully");
    }

    @Test
    public static void toVerifyUserShouldBeAblToAddProductToTheAddToCartSuccessfully(){
        //click on electronics
        clickOnElement(By.xpath("//div[@class='header-menu']/ul[1]/li[2]/a"));
        //click on photo and camera
        clickOnElement(By.xpath("//div[@class='page category-page']/div[2]/div[1]/div/div[1]/div/h2"));
        //click on add to cart
        clickOnElement(By.xpath("//div[@class='products-wrapper']/div/div/div[3]/div/div[2]/div[3]/div[2]/button[1]"));
        //click on add to cart button
        clickOnElement(By.className("cart-label"));
        //show the product in the cart
        clickOnElement(By.className("cart"));
        String actualMessage=getTextFromElement(By.className("no-data"));
        System.out.println("My Message: "+ actualMessage);
        Assert.assertEquals(actualMessage,expectedProductComparisonCompletionMessage,"User can not able compare the product successfully successfully");

    }

    @Test
    public static void toVerifyUserShouldBeAbleToReferToAFriendSuccessfully(){
        // Open register page and fill all mandatory details in register form
        clickOnElement(By.className("ico-register"));
        typeText(By.id("FirstName"), "Test");
        typeText(By.id("LastName"), "Suit");
        typeText(By.name("Email"), "testsuit11@gmail.com");
        typeText(By.id("Password"), "abc1234");
        typeText(By.id("ConfirmPassword"), "abc1234");
        // Complete registration
        clickOnElement(By.id("register-button"));
        // Open Login Page
        clickOnElement(By.className("ico-login"));
        // Enter your email address
        typeText(By.id("Email"), "testsuit11@gmail.com");
        // Enter Password
        typeText(By.id("Password"), "abc1234");
        // Click Log in button
        clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
        // Choose Apple MacBook Pro 13-inch
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        // Go to email friend
        clickOnElement(By.className("email-a-friend"));
        // Put friend's email address
        typeText(By.className("friend-email"), "bdfba@gmail.com");
        // Type message
        typeText(By.id("PersonalMessage"), "This MacBook is a best");
        // Click on Send email button
        clickOnElement(By.name("send-email"));
        // End of the process Actual message will come
        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        System.out.println("My Message:"+actualMessage);
        // Expected message
        Assert.assertEquals(actualMessage,expectedReferProductMessage, "Successfully message send");
    }
    @Test
    public static void verifyUserShouldBeAbleToVote(){
        // Open register page and fill all mandatory details in register form
        clickOnElement(By.className("ico-register"));
        typeText(By.id("FirstName"), "Test");
        typeText(By.id("LastName"), "Suit");
        typeText(By.name("Email"), "testsuit11@gmail.com");
        typeText(By.id("Password"), "abc1234");
        typeText(By.id("ConfirmPassword"), "abc1234");
        // Complete registration
        clickOnElement(By.id("register-button"));
        // Open Login Page
        clickOnElement(By.className("ico-login"));
        // Enter your email address
        typeText(By.id("Email"), "testsuit11@gmail.com");
        // Enter Password
        typeText(By.id("Password"), "abc1234");
        // Click Log in button
        clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
        // Choose polling option
        clickOnElement(By.id("pollanswers-2"));
        // Do Vote
        clickOnElement(By.id("vote-poll-1"));
        // End of the process Actual message will come
        String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("My Message:"+actualMessage);
        // Expected message
        Assert.assertEquals(actualMessage,expectedAbleToVoteSuccessfullMessage, "registerd user not be able to vote successfully");
    }

    }










