package com.aexp.qa.nameofyourproject.steps;

import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.NavigateToURL;
import com.aexp.qa.testframework.actions.ValidateTitle;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Opens an expected URL.
 */
public class OpenURLStep extends BaseStep {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(OpenURLStep.class);

    /** URL to userName. **/
    private String url;

    /** Expected titled in th opened URL. **/
    private String expectedTittle;

    /**
     * URL must not be null.
     */
    public void checkPreconditions() {
        Preconditions.checkNotNull(url);
    }

    /**
     * Step execution.
     * @param browserSession the browser session.
     */
    public void doExecute(final WebDriver browserSession) {
        new NavigateToURL(url).doAction(browserSession);
    }

    /**
     * Validates the step execution.
     * @param browserSession the browser session.
     */
    public void validateStepExecution(final WebDriver browserSession) {
        if (expectedTittle != null) {
            logger.info("Validating the expected tittle: " + expectedTittle);
            ValidateTitle validateTitle = new ValidateTitle();
            validateTitle.setExpectedTitle(expectedTittle);
            validateTitle.doAction(browserSession);
        }
    }

    /**
     * Validates the expected step output.
     * @param browserSession the browser session.
     */
    public void validateOutput(final WebDriver browserSession) {

    }

    /**
     * Sets the URL.
     * @param url the URL.
     * @return the builder.
     */
    public OpenURLStep setURL(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Gets the URL.
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the expected title.
     * @return the expected title.
     */
    public String getExpectedTittle() {
        return expectedTittle;
    }

    /**
     * Sets the expected title.
     * @param expectedTittle the expected title.
     * @return the builder.
     */
    public OpenURLStep setExpectedTittle(final String expectedTittle) {
        this.expectedTittle = expectedTittle;
        return this;
    }
}
