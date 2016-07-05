package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.ElementNotFoundActionException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Implements the double click an element action.
 * Created by vponn1 on 08/11/2015.
 */
public class DoubleClickElement extends ElementAction {

	/** Logger. **/
	public static final Logger logger = Logger.getLogger(DoubleClickElement.class);

	/**
	 * Instantiates a new element action.
	 */
	public DoubleClickElement() {
	}

	/**
	 * Instantiates the new action.
	 *
	 * @param element the element.
	 */
	public DoubleClickElement(final By element) {
		super(element);
	}

	/**
	 * Action implementation.
	 * @param browserSession the browser session.
	 */
	public void doAction(final WebDriver browserSession) {

		logger.info("Action DoubleClickOnElement \"" + getElement() + "\"");

		if (isElementPresent(browserSession)) {
			logger.info(getElement() + " exists. Double clicking on element...");
			Actions action = new Actions(browserSession);
			WebElement element = browserSession.findElement(getElement());
			action.doubleClick(element).perform();
		} else {
			logger.info(getElement() + " doesn't exists");
			throw new ElementNotFoundActionException("Element \"" + getElement() + "\" not found.");
		}
	}
}
