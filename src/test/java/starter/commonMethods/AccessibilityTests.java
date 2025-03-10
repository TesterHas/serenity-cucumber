package starter.commonMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import net.serenitybdd.core.Serenity;

public class AccessibilityTests {
    @Test
    public void verifyAccessibility() {
        System.out.println("Running Pa11y script...");
        runPa11y(TodoHomePage.url);

        System.out.println("Reading Pa11y report...");
        String pa11yReport = readPa11yReport("pa11y-report.json");

        System.out.println("Embedding Pa11y report in Serenity...");
        Serenity.recordReportData()
                .withTitle("Pa11y Accessibility Report")
                .andContents(pa11yReport);

        System.out.println("Test completed.");
    }

    private void runPa11y(String url) {
        try {
            System.out.println("Executing Pa11y script for URL: " + url);
            String command = "C:\\Program Files\\nodejs\\node.exe C:\\Users\\hiz22\\Downloads\\serenity-cucumber\\pa11y-runner.js " + url;
            Process process = Runtime.getRuntime().exec(command);
    
            // Capture standard output
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        System.out.println("Standard Output:");
        String line;
        while ((line = outputReader.readLine()) != null) {
            System.out.println(line);
        }

        // Capture error output
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        System.err.println("Error Output:");
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Pa11y script failed with exit code: " + exitCode);
        }

        System.out.println("Pa11y script completed successfully.");
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Error running Pa11y: " + e.getMessage());
    }
}

    private String readPa11yReport(String filePath) {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            throw new RuntimeException("Pa11y report file not found: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Pa11y report: " + e.getMessage());
        }

        return content.toString();
    }
}
