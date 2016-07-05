package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.Action;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * If an alert message is present, switches to alert and take the requested Action.
 * Created by vponn1 on 10/11/2015.
 */
public class AlertAction implements Action {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(AlertAction.class);

    /** Option name. **/
    private Option option;

    /**
     * Alert options.
     */
    public enum Option {
        /** Accept the alert. **/
        Accept;
    }

    /**
     * Instantiates a new alert action.
     */
    public AlertAction() {
    }

    /**
     * Instantiates a new alert action setting the option.
     * @param option the option.
     */
    public AlertAction(final Option option) {
        this.option = option;
    }

    /**
     * Sets the Option.
     * @param option the option for the alert message.
     * @return this action.
     */
    public AlertAction setOption(final Option option) {
        this.option = option;
        return this;
    }

    /**
     * Action implementation.
     * @param webDriver the browser session.
     */
    public void doAction(final WebDriver webDriver) {
        Alert alert;

        // Checks if alert is present.
        try {
            alert = webDriver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            logger.warn("No Alert present!");
            return;
        }

        // If present takes the requested action.
        if (option != null && option.equals(Option.Accept)) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }
}
