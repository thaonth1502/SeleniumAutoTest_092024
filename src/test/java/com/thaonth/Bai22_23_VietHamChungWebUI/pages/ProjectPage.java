package com.thaonth.Bai22_23_VietHamChungWebUI.pages;

import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProjectPage extends CommonPage {

    private WebDriver driver;
    public ProjectPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        new WebUI(driver);
    }
    private By buttonNewProject = By.xpath("//a[normalize-space()='New Project']");
    private By inputProjectName = By.xpath("//input[@id='name']");
    private By selectCustomer = By.xpath("//button[@data-id='clientid']");
    private By inputSearchCustomer = By.xpath("//button[@data-id='clientid']/following-sibling::div//input");
    private By itemCustomerName  = By.xpath("//span[@class = 'text']");
    private By selectBillingType = By.xpath("//button[@data-id='billing_type']");
    private By selectStatus = By.xpath("//button[@data-id='status']");
    private By inputTotalRate = By.xpath("//input[@id='project_cost']");
    private By inputEstimatedHours = By.xpath("//input[@id='estimated_hours']");
    private By selectMembers = By.xpath("//button[@data-id='project_members[]']");
    private By inputSearchMember = By.xpath("//button[@data-id='project_members[]']/following-sibling::div//input");
    private By inputStartDate = By.xpath("//input[@id='start_date']");
    private By inputDeadline = By.xpath("//input[@id='deadline']");
    private By inputDescription = By.xpath("");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");



    public void clickAddNewProject(){
        WebUI.clickElement(buttonNewProject);
    }

    public void checkCustomerDisplayInProjectForm(String customerName) {
        WebUI.clickElement(selectCustomer);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer, customerName);
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getElementText(itemCustomerName), customerName, "FAIL!!! The Customer not display in Project form");
    }

    public void selectBillingType(String billingType) {
        WebUI.clickElement(selectBillingType);
        WebUI.clickElement(By.xpath("//span[normalize-space()='"+billingType+"']"));
    }
    public void selectStatus(String status) {
        WebUI.clickElement(selectStatus);
        WebUI.clickElement(By.xpath("//span[normalize-space()='"+status+"']"));
    }


}
