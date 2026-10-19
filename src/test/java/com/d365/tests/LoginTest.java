package com.d365.tests;

import com.d365.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class LoginTest {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false));

        Page page = browser.newPage();

        page.navigate("https://orgd7be1f1d.crm8.dynamics.com");

       LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                "sk@shruthanjana.onmicrosoft.com",
                "your_password");
        System.out.println("Approve Microsoft Authenticator now...");    
        // waiting for 60 seconds to allow user to approve the Microsoft Authenticator request
        page.waitForTimeout(60000); 
        System.out.println("login Successful");
        System.out.println(page.title());
        
        page.frameLocator("iframe[title='AppLandingPage']")
    .getByRole(
        AriaRole.LINK,
        new FrameLocator.GetByRoleOptions()
            .setName("Sales Hub Easy-to-use sales"))
    .click();         

        page.waitForLoadState();

        System.out.println("Sales Hub Opened");
        System.out.println(page.title());
        page.waitForTimeout(10000);

        browser.close();
        playwright.close();
    }
}