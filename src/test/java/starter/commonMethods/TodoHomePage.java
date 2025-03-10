package starter.commonMethods;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://todomvc.com/examples/react/dist/#/")
public class TodoHomePage extends PageObject {
    public static Target INPUT_FIELD = Target.the("input field")
            .locatedBy("//input[@id='todo-input']");

            public static Target TASK_LISTED = Target.the("the task {0}")
                .locatedBy("//label[contains(text(),'{0}')]");

}
