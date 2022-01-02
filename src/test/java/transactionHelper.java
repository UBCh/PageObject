import static com.codeborne.selenide.Selenide.$;

public class transactionHelper {

      LoginPage loginPage=new LginPage();

      private static void Transaction1 (){
            loginPage.doneUser();
            $(".button__text").click();
            $("[data-test-id='amount'] input").setValue("1000");
            $("[data-test-id='from'] input").setValue("1000");


      }
    
}
