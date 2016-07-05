package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.ElementNotFoundActionException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Implements action of filling an element.
 * Created by cteix4 on 08/11/2015.
 */
public class SendKeysToElement extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(SendKeysToElement.class);

    /** The text keys. **/
    private String keys;

    /**
     * Instantiates a new element action.
     */
    public SendKeysToElement() {
    }

    /**
     * Instantiates the new action.
     *
     * @param element the element.
     */
    public SendKeysToElement(final By element) {
        super(element);
    }

    /**
     * Instantiates the new action.
     *
     * @param element the element.
     * @param keys the keys.
     */
    public SendKeysToElement(final By element, final String keys) {
        super(element);
        this.keys = keys;
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {

        logger.info("Action SendKeysToElement \"" + getElement() + "\"");

        if (isElementPresent(browserSession)) {
            logger.info(getElement() + " exists");
            logger.info("Sending keys \"" + keys + "\" to element " + getElement());
            browserSession.findElement(getElement()).sendKeys(keys);
        }	else {
            logger.info(getElement() + " doesn't exists");
            throw new ElementNotFoundActionException("Element \"" + getElement() + "\" not found.");
        }
    }

    /**
     * Gets the keys.
     * @return the text keys.
     */
    public String getKeys() {
        return keys;
    }

    /**
     * Sets the text keys.
     * @param keys the text keys.
     * @return this actions.
     */
    public SendKeysToElement setKeys(final String keys) {
        this.keys = keys;
        return this;
    }
}
