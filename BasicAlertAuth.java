package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Implements windows basic authentication.
 * Created by vponn1 on 12/16/2015.
 */
public class BasicAlertAuth extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(BasicAlertAuth.class);

    /** The text username. **/
    private String username;

    /** The text password. **/
    private String password;

    /**
     * Instantiates the new action.
     */
    public BasicAlertAuth() {
    }

    /**
     * Instantiates the new action setting the credential.
     * @param username the username.
     * @param password the password.
     */
    public BasicAlertAuth(final String username, final String password) {
        this.username = username;
        this.password = password;
        logger.info("Login: username:  " + username + ", password: " + password);
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
    	username = getUsername();
    	password = getPassword();

        logger.info("Sending the username" + username + ", password: " + password + " to Alert");
        logger.info("Action BasicAlertAuth ");

        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);

        try {
            // Code to Login
            Alert alert = browserSession.switchTo().alert();
            alert.sendKeys(username);

            // create robot for keyboard operations
            Robot rb = new Robot();
            new SyncTime().doAction(browserSession);
            //tab to password entry field
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyRelease(KeyEvent.VK_TAB);
            new SyncTime().doAction(browserSession);

            //Enter password by ctrl-v
            StringSelection pwd = new StringSelection(password);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            //press enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            logger.error("Not able to log in: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
<<<<<<< HEAD
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the text keys.
=======
     * Sets the username and password.
>>>>>>> 9cde81c3b021e74c885349db6b2c43094729c9e7
     * @param username the username.
     * @param password the password.
     * @return this actions.
     */
    public BasicAlertAuth setCredential(final String username, final String password) {
        this.username = username;
        this.password = password;
        return this;
    }
}
