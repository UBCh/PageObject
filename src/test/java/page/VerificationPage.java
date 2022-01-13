package page;
import com.codeborne.selenide.SelenideElement;

import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class VerificationPage {
  private SelenideElement veriPage =$(".paragraph");
    private static SelenideElement verifycode = $("[data-test-id='code'] input");
    private static SelenideElement verifyButton = $(".button__text");

    public VerificationPage() {
        veriPage.shouldBe(visible);
    }

    public static DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        verifycode.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

}
