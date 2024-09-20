package com.thaonth.Bai24_Parameter_MultiBrowser;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoParameter {

    @Test
    @Parameters({"number1", "number2"})
    public void sum(int n1, int n2){
        int finalSum = n1 + n2;
        System.out.println("Sum: " + finalSum);
    }

    @Test
    @Parameters({"email", "password"})
    public void login(String v1, String v2){
        System.out.println("Email: " + v1);
        System.out.println("Password: " + v2);
    }

    @Test
    @Parameters({"url", "email", "password"})
    public void login(String v1, String v2, String v3){
        System.out.println("URL: " + v1);
        System.out.println("Email: " + v2);
        System.out.println("Password: " + v3);
    }
}
