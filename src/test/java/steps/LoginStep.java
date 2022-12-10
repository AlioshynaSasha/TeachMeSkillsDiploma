package steps;

import models.User;
import pages.DashboardPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.page;

public class LoginStep {
    public LoginPage loginPage;

    public LoginStep() {
        loginPage = new LoginPage();
    }

    public void login(User user) {
        loginPage.emailInput.setValue(user.getLogin());
        loginPage.passwordInput.setValue(user.getPassword());
        loginPage.loginButton.click();
    }

    public DashboardPage loginSuccessful(User user) {
        login(user);

        return page(DashboardPage.class);
    }
}
