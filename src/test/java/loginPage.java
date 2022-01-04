import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

 public class loginPage {
    public loginPage() {
    }

    public static void DoneUser(){
        open("http://localhost:9999");
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue("qwerty123");
        $(".button__text").click();
        $("[data-test-id='code'] input").setValue("12345");
        $(".button__text").click();
    }

     // тут методы извлечения номера карты и баланса
}
