package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.ElementNotFoundActionException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Implements the click an element action.
 * Created by cteix4 on 08/11/2015.
 */
public class GetTextOfElement extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(GetTextOfElement.class);

    /**
     * Instantiates the new action setting the element.
     *
     * @param element the element.
     */
    public GetTextOfElement(final By element) {
        super(element);
    }

    /**
     * Instantiates a new element action.
     */
    public GetTextOfElement() {
        super();
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {

        logger.info("Action GetTextOfElement \"" + getElement() + "\"");

        if (isElementPresent(browserSession)) {
            String text = browserSession.findElement(getElement()).getText();
            logger.info(getElement() + " exists. Text of element is..." + text);
        } else {
            logger.error(getElement() + " doesn't exist...");
            throw new ElementNotFoundActionException("Element \"" + getElement() + "\" not found.");
        }
    }
}
