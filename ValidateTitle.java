package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.Action;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Class that implements the action to fetch the title and validate.
 * Created by cteix4 on 08/11/2015.
 */
public class ValidateTitle implements Action {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(ValidateTitle.class);

    /** The expected title for the current URL. **/
    private String expectedTitle;

    /**
     * Instantiates a new element action.
     */
    public ValidateTitle() {
    }

    /**
     * Instantiates a new element action.
     * @param expectedTitle the expected title.
     */

    public ValidateTitle(final String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        logger.info("Validating title equals \"" + expectedTitle + "\"");
        String actualTitle = browserSession.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title: ");
    }

    /**
     * Gets the expected title.
     * @return the expected title.
     */
    public String getExpectedTitle() {
        return expectedTitle;
    }

    /**
     * Sets the expected title.
     * @param expectedTitle the expected title.
     * @return this action.
     */
    public ValidateTitle setExpectedTitle(final String expectedTitle) {
        this.expectedTitle = expectedTitle;
        return this;
    }
}
