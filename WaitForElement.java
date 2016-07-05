package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.constants.Constants;
import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.ElementNotFoundActionException;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

// TODO Modify this wait for element action considering implicit waiting time.
/**
 * Waits for an element to be available.
 * Created by cteix4 on 08/11/2015.
 */
public class WaitForElement extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(WaitForElement.class);

    /** How long to wait before next try in seconds. **/
    public static final int RETRY_WAIT_TIME = 2;

    /** Max number of retries. **/
    public static final int MAXIMUM_RETRIES = 5;

    /**
     * Instantiates the new action.
     *
     * @param element the element.
     */
    public WaitForElement(final By element) {
        super(element);
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        Preconditions.checkNotNull(getElement(), "null elementID. Cannot wait for a null element ID");

        boolean found;
        int waitTime = 0;
        int retry = 0;
        do {
            logger.info("Waiting for element \"" + getElement() + "\" for " + (waitTime + RETRY_WAIT_TIME) + "s...");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(RETRY_WAIT_TIME));
                waitTime += RETRY_WAIT_TIME;
            } catch (InterruptedException e) {
                logger.error("Error sleeping... " + e.getMessage());
            }
            found = isElementPresent(browserSession);
            retry += 1;
        } while ((!found) && (waitTime < Constants.MAXIMUM_WAIT_TIME) && (retry < MAXIMUM_RETRIES));

        if (found) {
            logger.info("Element " + getElement() + " is present.");
        } else {
            logger.info("Element " + getElement() + " is NOT present after " + waitTime + "s and " + retry + " retries!");
            throw new ElementNotFoundActionException("Element \"" + getElement() + "\" not found.");
        }
    }
}
