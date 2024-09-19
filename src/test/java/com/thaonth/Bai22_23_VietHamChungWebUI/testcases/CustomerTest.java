package com.thaonth.Bai22_23_VietHamChungWebUI.testcases;

import com.thaonth.Bai22_23_VietHamChungWebUI.pages.CustomerPage;
import com.thaonth.Bai22_23_VietHamChungWebUI.pages.DashboardPage;
import com.thaonth.Bai22_23_VietHamChungWebUI.pages.LoginPage;
import com.thaonth.Bai22_23_VietHamChungWebUI.pages.ProjectPage;
import com.thaonth.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
   private LoginPage loginPage;
   private DashboardPage dashboardPage;
   private CustomerPage customerPage;
   private ProjectPage projectPage;

    @Test
    public void testAddNewCustomer(){
        loginPage = new LoginPage(driver);
        String CUSTOMER_NAME = "1909A6 Customer Name";
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customerPage = dashboardPage.clickMenuCustomers();
        int beforeTotalCustomers = Integer.parseInt(customerPage.getTotalCustomers());
        System.out.println("\uD83C\uDF40 Total Customer before: " + beforeTotalCustomers);
        customerPage.clickAddNewButton();
        customerPage.inputDataInAddNewCustomerForm(CUSTOMER_NAME);
        customerPage.clickSaveButton();
        int afterTotalCustomers = Integer.parseInt(customerPage.getTotalCustomers());
        System.out.println("\uD83C\uDF40  Total Customer after: " + afterTotalCustomers);
        Assert.assertEquals(afterTotalCustomers, beforeTotalCustomers + 1, "\uD83D\uDC1E FAIL!!! Total Customer not match.");
        customerPage.checkCustomerInTableList(CUSTOMER_NAME);
        customerPage.checkCustomerDetail(CUSTOMER_NAME);
        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInProjectForm(CUSTOMER_NAME);

    }

    @Test
    public void createNewGroupSuccess(){
        loginPage = new LoginPage(driver);
        String GROUP_NAME = "TEST A02";
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        customerPage.createNewGroup(GROUP_NAME);
        customerPage.verifyCreateNewGroupSuccessful("Customer Group added successfully.");
        customerPage.verifyGroupNameInCustomerForm(GROUP_NAME);
    }

    @Test
    public void createNewGroupFail(){
        loginPage = new LoginPage(driver);
        String GROUP_NAME = "";
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.clickAddNewButton();
        customerPage.createNewGroup(GROUP_NAME);
        customerPage.verifyCreateNewGroupFail("This field is required.");
    }
}
