package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.Action;
import com.aexp.wsgcat.seleniumframework.constants.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Class that implements the action to fetch the body content and validates if a sentence is present.
 * Created by cteix4 on 12/22/2015.
 */
public class ValidateBodyContent implements Action {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(ValidateBodyContent.class);

    /** How long to wait before next try in seconds. **/
    public static final int RETRY_WAIT_TIME = 1;

    /** The expected content be present in the body. **/
    private String expectedContent;

    /** the maximum wait time. **/
    private int maximumWaitTime = Constants.MAXIMUM_WAIT_TIME;

    /**
     * Instantiates a new element action.
     */
    public ValidateBodyContent() {
    }

    /**
     * Instantiates a new action.
     * @param expectedContent the expected title.
     */

    public ValidateBodyContent(final String expectedContent) {
        this.expectedContent = expectedContent;
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        logger.info("Validating body contains \"" + expectedContent + "\"");

        boolean found;
        int waitTime = 0;
        int retry = 0;
        do {
            logger.info("Waiting for content \"" + expectedContent + "\" appear - " + (waitTime + RETRY_WAIT_TIME) + "s...");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(RETRY_WAIT_TIME));
                waitTime += RETRY_WAIT_TIME;
            } catch (InterruptedException e) {
                logger.error("Error sleeping... " + e.getMessage());
            }
            found = isElementPresent(browserSession);
            retry += 1;
        } while ((!found) && (waitTime < maximumWaitTime) && (retry < maximumWaitTime));

        logger.info("Content \"" + expectedContent + "\" found in the current page? " + found);
        Assert.assertTrue(found, "Content \"" + expectedContent + "\" not found in the current page \"" + browserSession.getTitle() + "\"");
    }

    /**
     * Detects if content is present in the page.
     * @param browserSession the browser session.
     * @return true if the content is present, otherwise, false.
     */
    public boolean isElementPresent(final WebDriver browserSession) {
        try {
            return (browserSession.findElement(By.tagName("body")).getText().contains(expectedContent));
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Gets the expected content.
     * @return the expected content.
     */
    public String getExpectedContent() {
        return expectedContent;
    }

    /**
     * Sets the expected content.
     * @param expectedContent the expected content.
     * @return this action.
     */
    public ValidateBodyContent setExpectedContent(final String expectedContent) {
        this.expectedContent = expectedContent;
        return this;
    }

    /**
     * Sets the wait time when looking for the body content.
     * @param maximumWaitTime the maximum wait time.
     * @return this class.
     */
    public ValidateBodyContent setMaximumWaitTime(final int maximumWaitTime) {
        this.maximumWaitTime = maximumWaitTime;
        return this;
    }
}
