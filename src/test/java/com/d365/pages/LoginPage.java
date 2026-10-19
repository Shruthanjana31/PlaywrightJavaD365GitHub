package com.d365.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String email, String password) {

        page.fill("#i0116", email);
        page.click("#idSIButton9");

        page.waitForTimeout(3000);

        page.fill("#i0118", password);
        page.click("#idSIButton9");

        page.waitForTimeout(3000);

        page.click("#idSIButton9");
    }
}