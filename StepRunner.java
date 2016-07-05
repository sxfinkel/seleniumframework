package com.aexp.wsgcat.seleniumframework;

import com.aexp.wsgcat.seleniumframework.constants.Constants;
import com.beust.jcommander.internal.Lists;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.Iterator;
import java.util.List;

/**
 * Responsible to execute steps for a test case.
 * Created by cteix4 on 08/11/2015.
 */
public final class StepRunner {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(Step.class);

    /** Driver to run the the test case. **/
    private WebDriver browserSession;

    /** Initial time. **/
    private long startTime;

    /** Final time. **/
    private long endTime;

    /** List of steps to be executed for a test case. **/
    private List<Step> testCaseSteps = Lists.newArrayList();

    /**
     * Test runner initiates the browser.
     * @param browserSession the browser session.
     */
    public StepRunner(final WebDriver browserSession) {
        this.browserSession = browserSession;
    }

    /**
     * Executes callback methods {@Link Step#validateInput}, {@Link Step#doExecute}, {@Link Step#validateStepExecution} and {@Link Step#validateOutput} for
     * each step.
     *
     * @throws Exception when occurs an error running the step.
     */
    public void start() throws Exception {

        // Save the driver as an attribute in the test result. This will help to quit the brownser after the method is executed.
        logger.info("Saving the driver in the test context");
        Reporter.getCurrentTestResult().setAttribute(BaseHTMLReporter.DRIVER_ATTRIBUTE, browserSession);

        Iterator<Step> stepIterator = testCaseSteps.iterator();
        Step currentStep;
        StringBuffer testStepsInfo = new StringBuffer();
        testStepsInfo.append("<table width=\"100%\" word-wrap:\"break-word\">");

        while (stepIterator.hasNext()) {
            currentStep = stepIterator.next();
            logger.info("[" + currentStep.getClass().getSimpleName() + "] Starts step run...");
            startTime = System.currentTimeMillis();
            boolean passed = false;

            try {
                logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Precondition...");
                currentStep.checkPreconditions();
                currentStep.takeScreenshot(browserSession, "Before");

                logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Execution...");
                currentStep.doExecute(browserSession);
                currentStep.takeScreenshot(browserSession, "After");

                logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Validating execution...");
                currentStep.validateStepExecution(browserSession);

                if (currentStep.isSkipFullValidation()) {
                    logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Validating output...");
                } else {
                    logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Validating output...");
                    currentStep.validateOutput(browserSession);
                }
                passed = true;

                logger.info(Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + ": Step finished.");
            } catch (AssertionError error) {
                currentStep.takeScreenshot(browserSession, "AssertionError");
                logger.error(error.getMessage(), error);
                throw error;
            } catch (Exception e) {
                currentStep.takeScreenshot(browserSession, "Exception");
                logger.error(e.getMessage(), e);
                throw e;
            } finally {
                endTime = System.currentTimeMillis();
                logger.info("[" + Reporter.getCurrentTestResult().getName() + ": " + currentStep.getClass().getSimpleName() + "] Completed after "
                        + (endTime - startTime) + "s...");
                // Red for fail and green for passed
                if (passed) {
                    testStepsInfo.append("<tr><td class=\"shrink\"><font color='#33CC33'><b><i>V</i></b></font></td><td><span class=\"inner-pre\" "
                            + "style=\"font-size: 11px\">");
                } else {
                    testStepsInfo.append("<tr><td class=\"shrink\"><font color='#E80000'><b><i>X</i></b></font></td><td><span class=\"inner-pre\" "
                            + "style=\"font-size: 11px\">");
                }
                testStepsInfo.append(currentStep.toString());
                testStepsInfo.append("</span></td><td class=\"shrink\"><pre>");
                testStepsInfo.append(getScreenshotLinks(currentStep));
                testStepsInfo.append("</pre></td class=\"shrink\"></tr>");
                // close the table at the end and saves the test info.
                if (!passed || !stepIterator.hasNext()) {
                    testStepsInfo.append("</table>");
                    Reporter.getCurrentTestResult().setAttribute(ReportUtils.TEST_INFO_KEY, testStepsInfo.toString());
                }
            }
        }
    }

    /**
     * Gets all screenshot links for the .
     * @param step the step.
     * @return the links.
     */
    private String getScreenshotLinks(final Step step) {
        StringBuffer screenshotLinks = new StringBuffer();
        for (String key : step.fetchScreenshotFiles().keySet()) {
            String link = step.fetchScreenshotFiles().get(key).getName();

            // if link was saved in the subfolder
            if (step.fetchScreenshotFiles().get(key).getPath().contains(Constants.SCREENSHOT_SUBFOLDER)) {
                link = "..\\" + Constants.SCREENSHOT_SUBFOLDER + "\\" + link;
            }
            screenshotLinks.append("<a href=\"" + link + "\" target=\"_blank\">" + key + "</a> ");
        }
        return screenshotLinks.toString();
    }

    /**
     * Adds a new step in test case list.
     * @param newStep the new step to be added.
     * @return the test runner.
     */
    public StepRunner add(final Step newStep) {
        this.testCaseSteps.add(newStep);
        return this;
    }

    /**
     * Gets the setBrowserSession used in the step.
     * @return the setBrowserSession.
     */
    public WebDriver getBrowserSession() {
        return browserSession;
    }

    /**
     * Sets the setBrowserSession that will be used in the step.
     * @param browserSession the setBrowserSession.
     * @return the test runner.
     */
    public StepRunner setBrowserSession(final WebDriver browserSession) {
        this.browserSession = browserSession;
        return this;
    }
}
