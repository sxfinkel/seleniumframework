package com.aexp.wsgcat.seleniumframework;

import com.aexp.wsgcat.seleniumframework.trackingservice.TrackingProfile;
import com.aexp.wsgcat.seleniumframework.trackingservice.TrackingUtility;
import com.saucelabs.testng.SauceOnDemandTestListener;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Listener to be used in the test and information could be added for the report.
 * Created by cteix4 on 08/11/2015.
 * Updated by bvenkatr on 01/21/2015.
 */
public class BaseHTMLReporter extends HTMLReporter implements ITestListener {

    /** Driver attribute used in the tests. **/
    public static final String DRIVER_ATTRIBUTE = "driver";

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(BaseHTMLReporter.class);

    /** Utils. **/
    private static final String UTILS_KEY = "utils";

    /** Report utils. **/
    private static final ReportUtils REPORT_UTILS = new ReportUtils();

    /** Sauce labs listener. **/
    private SauceOnDemandTestListener sauceOnDemandTestListener = new SauceOnDemandTestListener();

    /** Listener suite. **/
    private List<ISuite> suites;

    /** Tracking Profile for service object. **/
    private TrackingProfile profile = new TrackingProfile();

    /** Date format Object for current date. **/
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /** calendar Object for current date. **/
    private Calendar cal = Calendar.getInstance();
    /**
     * Creates velocity context.
     * @return the context.
     */
    protected VelocityContext createContext() {
        VelocityContext context = super.createContext();
        context.put(UTILS_KEY, REPORT_UTILS);
        return context;
    }

    /**
     * Actions when test fails.
     * @param result
     *            the test result.
     */
    public void onTestFailure(final ITestResult result) {
        if (TestingContext.isStrategySauceLabs()) {
            sauceOnDemandTestListener.onTestFailure(result);
        }
    }

    /**
     * Actions when the test pass.
     * @param result
     *            the test result.
     */
    public void onTestSuccess(final ITestResult result) {
        if (TestingContext.isStrategySauceLabs()) {
            sauceOnDemandTestListener.onTestSuccess(result);
        }
    }

    /**
     * Actions when test starts.
     * @param result
     *            the test result.
     */
    public void onTestStart(final ITestResult result) {
        if (TestingContext.isStrategySauceLabs()) {
            sauceOnDemandTestListener.onTestStart(result);
        }
    }

    /**
     * Actions when test skipped.
     * @param result
     *            the test result.
     */
    public void onTestSkipped(final ITestResult result) {
    }

    /**
     * Actions when test partially failed.
     * @param result
     *            the test result.
     */
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }

    /**
     * Actions when test starts.
     * @param context
     *            test context.
     */
    public void onStart(final ITestContext context) {
        if (TestingContext.isStrategySauceLabs()) {
            sauceOnDemandTestListener.onStart(context);
        }
        profile.setStartTime(System.currentTimeMillis());
    }

    /**
     * Actions when test finishes.
     * @param context test context.
     */
    public void onFinish(final ITestContext context) {
        //set sub project ID and other data for tracking service
        profile.setSubProjectId(TestingContext.getEnvironmentValue("subproject_id"));
        profile.setNoOfPass(context.getPassedTests().size());
        profile.setNoOfFail(context.getFailedTests().size());
        profile.setManualEffort("300");
        profile.setScriptDevelopmentDate("01/01/2015");
        profile.setCategory("Tool");
        profile.setApplicationId("Test Tool Name");
        //Selenium Framework - Test Automation Execution
        profile.setRunDate(dateFormat.format(cal.getTime()));
        profile.setApplicationName("Test-Selenium Framework");
        profile.setScriptId(System.getProperty("user.name") + dateFormat.format(cal.getTime()));
        profile.setScriptDescription("Test-Selenium Framework");
        profile.setEndTime(System.currentTimeMillis());
        if (profile.getSubProjectId().isEmpty()) {
            logger.error("Error in Tracking saves for this execution. Sub Project ID is empty or incorrect");
        } else {
            logger.info("Saves for this execution is successfully tracked");
            TrackingUtility.trackingMechanism(profile);
        }
        NotificationSender.sendEmailNotification(suites);
    }

    @Override
    public void generateReport(final List<XmlSuite> xml, final List<ISuite> suites, final String outdir) {
        super.generateReport(xml, suites, outdir);
        NotificationSender.sendEmailNotification(suites);
    }
}
