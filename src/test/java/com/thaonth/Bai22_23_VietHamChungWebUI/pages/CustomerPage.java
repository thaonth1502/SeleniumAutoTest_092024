package com.thaonth.Bai22_23_VietHamChungWebUI.pages;

import static com.thaonth.keywords.WebUI.*;

import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class CustomerPage extends CommonPage {

    private WebDriver driver;

    public CustomerPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }

    //Elements
    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By inputSearchBox = By.xpath("//div[@id='clients_filter']/descendant::input");
    private By buttonNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By firstItemCustomerName = By.xpath("//tbody/tr[1]/td[3]/a");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVAT = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By searchGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By searchCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//input");
    private By dropdownDefaultLanguage = By.xpath("//button[@data-id='default_language']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By searchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By buttonSaveCustomer = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By alterMessage = By.xpath("//span[@class='alert-title']");
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");


    //Create Group
    private By buttonCreateGroup = By.xpath("//div[@class='input-group-btn']");
    private By inputNameGroup = By.xpath("//input[@id='name']");
    private By errorMessage = By.xpath("//p[@id='name-error']");
    private By buttonSaveGroup = By.xpath("//div[@id='customer_group_modal']//button[normalize-space()='Save']");




    //Hàm xử lý cho trang Customer
    //Create new Groups
    public String getTotalCustomers(){
        clickElement(menuCustomers);
        return getElementText(totalCustomers);
    }

    public void createNewGroup(String groupName){
        clickElement(buttonCreateGroup);
        driver.switchTo().activeElement();
        setText(inputNameGroup, groupName);
        clickElement(buttonSaveGroup);
        sleep(2);
    }

    public void verifyCreateNewGroupSuccessful(String message){
        Assert.assertTrue(checkElementExist(alterMessage), "\uD83D\uDC1E FAIL!!! Alert message not displayed.");
        assertEquals(getElementText(alterMessage), message, "\uD83D\uDC1E FAIL!!! The content message not match.");

    }
    public void verifyCreateNewGroupFail(String message){
        Assert.assertTrue(checkElementExist(errorMessage), "\uD83D\uDC1E FAIL!!! Error message not displayed.");
        assertEquals(getElementText(errorMessage), message, "\uD83D\uDC1E FAIL!!! The content error message not match.");
    }

    public void verifyGroupNameInCustomerForm(String groupName){
        selectGroup(groupName);
        assertEquals(getElementAttribute(dropdownGroups,"title"), groupName, "FAIL!!! The Groups not match.");
    }

    //Click button Save in Create new Customer form
    public void clickSaveButton(){
        clickElement(buttonSaveCustomer);
        sleep(2);
//        Assert.assertTrue(WebUI.getWebElement(alterMessage).isDisplayed(),"\uD83D\uDC1E FAIL!!! The alter Message not display.");
        Assert.assertTrue(isElementDisplayed(alterMessage), "\uD83D\uDC1E FAIL!!! The alter Message not display.");
        assertEquals(getElementText(alterMessage).trim(), "Customer added successfully.", "FAIL!!! The content message not match.");
    }

    public void clickAddNewButton(){
        clickElement(buttonNewCustomer);

    }

    public void inputDataInAddNewCustomerForm(String customerName){
        setText(inputCompany, customerName);
        setText(inputVAT, "10");
        setText(inputPhone, "098765432");
        setText(inputWebsite, "https://anhtester.com");
        selectGroup("VIP");
        selectCurrency("USD");
        selectLanguage("Vietnamese");
        sleep(1);
        setText(inputAddress, "Hanoi");
        setText(inputCity, "Hanoi");
        setText(inputState, "CauGiay");
        setText(inputZipCode, "100000");
        selectCountry("Vietnam");
    }

    public void selectCurrency(String currency){
        clickElement(dropdownCurrency);
        sleep(1);
        setText(searchCurrency, currency);
        sleep(1);
        setKeys(searchCurrency, Keys.ENTER);
        sleep(1);
    }

    public void selectGroup(String groupName){
        clickElement(dropdownGroups);
        setTextAndKey(searchGroup,groupName,Keys.ENTER);
        sleep(1);
        clickElement(dropdownGroups);
    }

    public void selectLanguage(String languageName){
        clickElement(dropdownDefaultLanguage);
        clickElement(By.xpath("//span[normalize-space()='"+languageName+"']"));
    }

    public void selectCountry(String countryName){
        clickElement(dropdownCountry);
        setTextAndKey(searchCountry,countryName,Keys.ENTER);
    }
    public void checkCustomerInTableList(String customerName){
        clickElement(menuCustomers);
        setText(inputSearchBox, customerName);
        sleep(2);
        Assert.assertTrue(checkElementExist(firstItemCustomerName), "FAIL!!! The customer name not display in table");
        assertEquals(getElementText(firstItemCustomerName), customerName, "FAIL!!! The customer not match");
    }
    public void checkCustomerDetail(String customerName){
        clickElement(firstItemCustomerName);
        //Check content customer detail

        assertEquals(getElementAttribute(inputCompany,"value"), customerName, "\uD83D\uDC1E FAIL!!! The customer Name not match.");
        assertEquals(getElementAttribute(inputVAT,"value"), "10", "\uD83D\uDC1E FAIL!!! The VAT not match.");
        assertEquals(getElementAttribute(inputPhone,"value"), "098765432", "\uD83D\uDC1E FAIL!!! The Phone not match.");
        assertEquals(getElementAttribute(inputWebsite,"value"), "https://anhtester.com", "\uD83D\uDC1E FAIL!!! The Website not match.");
        assertEquals(getElementAttribute(dropdownGroups,"title"), "VIP", "\uD83D\uDC1E FAIL!!! The Groups not match.");
        assertEquals(getElementAttribute(dropdownCurrency,"title"), "USD", "\uD83D\uDC1E FAIL!!! The Currency not match.");
        assertEquals(getElementAttribute(dropdownDefaultLanguage,"title"), "Vietnamese", "\uD83D\uDC1E FAIL!!! The Language not match.");
        assertEquals(getElementAttribute(inputAddress,"value"), "Hanoi", "\uD83D\uDC1E FAIL!!! The Address not match.");
        assertEquals(getElementAttribute(inputCity,"value"), "Hanoi", "\uD83D\uDC1E FAIL!!! The City not match.");
        assertEquals(getElementAttribute(inputState,"value"), "CauGiay", "\uD83D\uDC1E FAIL!!! The State not match.");
        assertEquals(getElementAttribute(inputZipCode,"value"), "100000", "\uD83D\uDC1E FAIL!!! The Zip Code not match.");
        assertEquals(getElementAttribute(dropdownCountry,"title"), "Vietnam", "\uD83D\uDC1E FAIL!!! The Country not match.");
    }
}
