package starter.stepdefinitions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.annotations.Step;
import starter.commonMethods.Pa11yUtil;

public class AccessibilitySteps extends UIInteractionSteps {

    @Step("Run accessibility test on {0}")
    public void runAccessibilityTest(String url) {
        Pa11yUtil.runPa11y(url);
    }
}
