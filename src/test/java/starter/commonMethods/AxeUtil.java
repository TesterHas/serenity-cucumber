package starter.commonMethods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AxeUtil {

    public static void analyzeAccessibility(WebDriver driver) {
        try {
            // Initialize AxeBuilder
            AxeBuilder axeBuilder = new AxeBuilder();

            // Run accessibility analysis
            Results results = axeBuilder.analyze(driver);

            // Save results to a JSON file in the target folder
            String filePath = "target/accessibility-results.json";
            saveResultsToFile(results, filePath);

            // Log results
            logResults(results);
        } catch (Exception e) {
            System.out.println("Error running accessibility analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveResultsToFile(Results results, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Serialize Results object to JSON and save to file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), results);
            System.out.println("Accessibility results saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving accessibility results: " + e.getMessage());
        }
    }

    private static void logResults(Results results) {
        List<Rule> violations = results.getViolations();
        if (violations.isEmpty()) {
            System.out.println("No accessibility violations found!");
        } else {
            System.out.println("Accessibility violations found:");
            for (Rule violation : violations) {
                System.out.println("Rule ID: " + violation.getId());
                System.out.println("Description: " + violation.getDescription());
                System.out.println("Help: " + violation.getHelp());
                System.out.println("Violated Elements: " + violation.getNodes().size());
                System.out.println("------------------------");
            }
        }
    }
}