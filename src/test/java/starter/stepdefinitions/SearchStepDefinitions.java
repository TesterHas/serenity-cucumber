package starter.stepdefinitions;

import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.commonMethods.NavigateTo;
import starter.commonMethods.TodoHomePage;
public class SearchStepDefinitions {

    @Given("{actor} is on the Todo App")
    public void is_on_the_Todo_app(Actor actor) {
        actor.wasAbleTo(NavigateTo.theTodoHomePage());
    }

    @When("{actor} adds a new task {string}")
    public void adds_a_new_task(Actor actor, String task) {
        actor.attemptsTo(
            Enter.theValue(task).into(TodoHomePage.INPUT_FIELD).thenHit(Keys.ENTER)
        );
    }

    @Then("{actor} should see the task {string} added to the list")
    public void should_see_the_task_added_to_the_list(Actor actor,String task) {
        actor.attemptsTo(
                Ensure.that(TodoHomePage.TASK_LISTED.of(task)).isDisplayed()
        );
    }
}
