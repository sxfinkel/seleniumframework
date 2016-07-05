package com.aexp.wsgcat.seleniumframework.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.constants.Constants;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Waits for the page to be available.
 * Created by bvenkatr on 01/21/2015.
 */
public class WaitForPageLoad extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(WaitForPageLoad.class);

    /** How long to wait before next try in seconds. **/
    public static final int RETRY_WAIT_TIME = 2;

    /** Max number of retries. **/
    public static final int MAXIMUM_RETRIES = 2;

    /**
     * Instantiates the new action.
     *
     * @param element the element.
     */
    public WaitForPageLoad(final By element) {
        super(element);
    }
    /**
     * Instantiates the new action.
     */
    public WaitForPageLoad() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        Preconditions.checkNotNull(browserSession, "null driver. Cannot wait for a null driver");
        WebDriverWait wait = new WebDriverWait(browserSession, Constants.MAXIMUM_WAIT_TIME);
        // wait for page to load
      	wait.until(new Predicate<WebDriver>() {
      		public boolean apply(final WebDriver driver) {
      			return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
      		}
      	});
    }
}
