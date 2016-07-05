package com.company.qa.seleniumframework;

import org.openqa.selenium.WebDriver;

/**
 * This interface represents a selenium action. All different actions like clickOnElement, openURL etc., must implement this interface.
 *
 * 
 */
public interface Action {

    /**
     * The selenium action must be implemented in this method.
     * @param webDriver the browser session.
     */
    void doAction(WebDriver webDriver);
