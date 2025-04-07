package WorkrigAutoamation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test listener for tracking test execution and reporting
 */
public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        logger.error("Failure reason: {}", result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished. Total tests: {}, Passed: {}, Failed: {}, Skipped: {}",
            context.getAllTestMethods().length,
            context.getPassedTests().size(),
            context.getFailedTests().size(),
            context.getSkippedTests().size());
    }
} 