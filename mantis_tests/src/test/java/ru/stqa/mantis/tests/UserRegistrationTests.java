package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var email = String.format("%s@localhost", CommonFunctions.randomString(8));
        app.jamesApi().addUser(email, "password");
        var username = String.format(CommonFunctions.randomString(8));
        app.session().signup(username, email);
        app.mail().getInbox(email, "password");
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(1000));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.session().verified(url,"root", "root");
        }
        app.http().login(username, "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
