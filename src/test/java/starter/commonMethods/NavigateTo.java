package starter.commonMethods;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theTodoHomePage() {
        return Task.where("{0} opens the DuckDuckGo home page",
                Open.browserOn().the(TodoHomePage.class));
    }
}
