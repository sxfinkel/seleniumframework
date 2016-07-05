package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class that helps to Switch to different frame.
 * Created by vponn1 on 08/11/2015.
 */

/** Switches to different frame. **/
public class SwitchToFrame extends ElementAction {

    /**
     * Instantiates a new element action.
     */
    public SwitchToFrame() {
    }

    /**
     * Instantiates the new action.
     *
     * @param element the element.
     */
    public SwitchToFrame(final By element) {
        super(element);
    }

    /**
     * Action implementation.
     * @param webDriver the browser session.
     */
    public void doAction(final WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Constants.MAXIMUM_WAIT_TIME);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElement()));
    }
}
