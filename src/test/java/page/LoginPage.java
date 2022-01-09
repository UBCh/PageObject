package page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    String login;
    String password;
    String hardcoded;
    int amount;
    String score1;
    String score2;
    int balanse1;
    int balanse2;

    public LoginPage() {
    }

    public LoginPage(String login, String password, String hardcoded, int amount, String score1, String score2, int balanse1, int balanse2) {
        this.login = login;
        this.password = password;
        this.hardcoded = hardcoded;
        this.amount = amount;
        this.score1 = score1;
        this.score2 = score2;
        this.balanse1 = balanse1;
        this.balanse2 = balanse2;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getHardcoded() {
        return hardcoded;
    }

    public int getAmount() {
        return amount;
    }

    public String getScore1() {
        return score1;
    }

    public String getScore2() {
        return score2;
    }

    public int getBalanse1() {
        return balanse1;
    }

    public int getBalanse2() {
        return balanse2;
    }
}
