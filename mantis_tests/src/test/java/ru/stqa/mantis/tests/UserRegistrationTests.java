package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canRegisterUser() {
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.session().signup(user.name(), email);
        app.mail().getInbox(email, "password");

        var message = app.developerMail().receive(user, Duration.ofSeconds(1000));
        var text = message;
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.session().verified(url,"root", "root");
        }
        app.http().login(user.name(), "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
