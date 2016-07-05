package com.aexp.wsgcat.seleniumframework;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * Represents a step of a test case.
 * Created by cteix4 on 08/11/2015.
 */
public interface Step {

    /**
     * Checks the input parameters.
     */
    void checkPreconditions();

    /**
     *  Executes the step functionality.
     * @param driver the web driver session to be used by the step.
     */
    void doExecute(final WebDriver driver);

    /**
     * Validates the step execution.
     * @param driver the web driver session to be used by the step.
     */
    void validateStepExecution(final WebDriver driver);

    /**
     * Full validation for the step. Validates the expect output.
     * @param driver the web driver session to be used by the step.
     */
    void validateOutput(final WebDriver driver);

    /**
     * Prints step information.
     * @return the step information.
     */
    String toString();

    /**
     * Takes a screenshot.
     * @param driver the test driver.
     * @param key key for the screenshot
     * @return the screenshot file.
     */
    File takeScreenshot(final WebDriver driver, final String key);

    /**
     * Gets all screenshot taken in the step.
     * @return the screenshot in a hashmap format.
     */
    LinkedHashMap<String, File> fetchScreenshotFiles();

    /**
     * Sets skip validation to true.
     * @return this step.
     */
    Step skipFullValidation();

    /**
     * Checks if step full validation should be skipped.
     * @return if to skip, true, else, false.
     */
    boolean isSkipFullValidation();

}
