
package com.automation.testdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;

public final class LoginDataProvider {

    private LoginDataProvider() {
    }

    // Store one login scenario without exposing credentials
    public record LoginCase(
            String scenario,
            String username,
            String password,
            String expected) {

        @Override
        public String toString() {
            return scenario;
        }
    }

    @DataProvider(name = "validLogins", parallel = true)
    public static Object[][] validLogins() {
        return loadCases("validLogins", "expectedUrl");
    }

    @DataProvider(name = "invalidLogins", parallel = true)
    public static Object[][] invalidLogins() {
        return loadCases("invalidLogins", "expectedError");
    }

    private static Object[][] loadCases(
            String groupName,
            String expectedField) {

        String filePath = "testdata/login-data.json";

        try (InputStream input = LoginDataProvider.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {

            if (input == null) {
                throw new IllegalStateException(
                        "Test data file not found: " + filePath);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(input);
            JsonNode cases = root.path(groupName);

            if (!cases.isArray() || cases.isEmpty()) {
                throw new IllegalStateException(
                        "Missing or empty test data: " + groupName);
            }

            Object[][] data = new Object[cases.size()][1];

            for (int i = 0; i < cases.size(); i++) {
                JsonNode item = cases.get(i);

                data[i][0] = new LoginCase(
                        required(item, "scenario"),
                        required(item, "username"),
                        required(item, "password"),
                        required(item, expectedField));
            }

            return data;

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read login test data", e);
        }
    }

    private static String required(
            JsonNode node, String field) {

        JsonNode value = node.get(field);

        if (value == null || !value.isTextual()
                || value.asText().isBlank()) {
            throw new IllegalStateException(
                    "Missing or invalid field: " + field);
        }

        return value.asText();
    }
}
