package starter.commonMethods;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pa11yUtil {

    public static void runPa11y(String url) {
        try {
            // Command to run Pa11y
            String command = "npx pa11y " + url;

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Pa11y process exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}