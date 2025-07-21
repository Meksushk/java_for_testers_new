package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void signup(String username, String email) {
        var signupLink = manager.driver().findElement(By.linkText("Signup for a new account"));
        signupLink.click();
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@value=\'Signup\']"));
    }

    public void verified(String url, String password, String confirmPassword) {
        manager.driver().get(url);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        var submit = manager.driver().findElement(By.cssSelector("button.btn-success"));
        submit.click();
    }
}

