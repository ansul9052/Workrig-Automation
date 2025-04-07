package WorkrigAutoamation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG Listener for reporting and logging test execution.
 * This class implements ITestListener to capture test events.
 */
public class TestListener implements ITestListener {
    
    private static final String LOG_DIR = "test-output/logs";
    private PrintWriter writer;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public void onStart(ITestContext context) {
        createLogFile();
        log("Test Suite: " + context.getName() + " started at " + dateFormat.format(new Date()));
    }
    
    @Override
    public void onFinish(ITestContext context) {
        log("Test Suite: " + context.getName() + " finished at " + dateFormat.format(new Date()));
        log("Total tests: " + context.getAllTestMethods().length);
        log("Passed tests: " + context.getPassedTests().size());
        log("Failed tests: " + context.getFailedTests().size());
        log("Skipped tests: " + context.getSkippedTests().size());
        
        if (writer != null) {
            writer.close();
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        log("Test started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        log("Test passed: " + result.getName());
        Reporter.log("Test passed: " + result.getName(), true);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        log("Test failed: " + result.getName());
        log("Error message: " + result.getThrowable().getMessage());
        Reporter.log("Test failed: " + result.getName(), true);
        Reporter.log("Error message: " + result.getThrowable().getMessage(), true);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        log("Test skipped: " + result.getName());
        Reporter.log("Test skipped: " + result.getName(), true);
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log("Test failed within success percentage: " + result.getName());
        Reporter.log("Test failed within success percentage: " + result.getName(), true);
    }
    
    /**
     * Create log file for test execution
     */
    private void createLogFile() {
        try {
            File logDir = new File(LOG_DIR);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            
            String fileName = "test-log-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".txt";
            File logFile = new File(logDir, fileName);
            
            writer = new PrintWriter(new FileWriter(logFile));
            log("Test execution log started at " + dateFormat.format(new Date()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Log message to file and console
     * @param message Message to log
     */
    private void log(String message) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = "[" + timestamp + "] " + message;
        
        System.out.println(logMessage);
        
        if (writer != null) {
            writer.println(logMessage);
            writer.flush();
        }
    }
} 