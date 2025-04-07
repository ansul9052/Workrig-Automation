package WorkrigAutoamation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing test data
 */
public class TestDataProvider {
    private static final Logger logger = LoggerFactory.getLogger(TestDataProvider.class);
    private static final Map<String, String> testData = new HashMap<>();

    static {
        // Initialize test data
        testData.put("validUsername", "your_valid_username");
        testData.put("validPassword", "your_valid_password");
        testData.put("invalidUsername", "invalid_user");
        testData.put("invalidPassword", "invalid_pass");
        testData.put("baseUrl", "https://workrig.com");
    }

    /**
     * Get test data by key
     * @param key The key to retrieve
     * @return The value associated with the key
     */
    public static String getTestData(String key) {
        logger.debug("Retrieving test data for key: {}", key);
        return testData.get(key);
    }

    /**
     * Add test data
     * @param key The key to add
     * @param value The value to add
     */
    public static void addTestData(String key, String value) {
        logger.debug("Adding test data - Key: {}, Value: {}", key, value);
        testData.put(key, value);
    }
} 