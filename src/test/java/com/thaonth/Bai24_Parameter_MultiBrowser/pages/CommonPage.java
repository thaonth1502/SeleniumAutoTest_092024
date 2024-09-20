package com.thaonth.Bai24_Parameter_MultiBrowser.pages;

import com.thaonth.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage {
    private WebDriver driver;

    public CommonPage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    public By menuSales = By.xpath("//li[@class='menu-item-sales']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

    public DashboardPage clickMenuDashboard(){
        WebUI.clickElement(menuDashboard);
        return new DashboardPage(driver);
    }
    public CustomerPage clickMenuCustomers(){
        WebUI.clickElement(menuCustomers);
        return new CustomerPage(driver);
    }

    public ProjectPage clickMenuProjects(){
        WebUI.clickElement(menuProjects);
        return new ProjectPage(driver);
    }

}
