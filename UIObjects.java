package com.aexp.wsgcat.seleniumframework;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains commons features for reading objects from properties and loading.
 * Created by bvenkatr on 1/21/2015.
 */

/**
 * Contains features to read properties file for UI objects.
 * Created by bvenkatr on 01/21/2015.
 */
public class UIObjects {

	 /** Logger. **/
    public static final Logger logger = Logger.getLogger(UIObjects.class);

    /**.
     * private constructor for utility class
     */
    private UIObjects() {
    	//sample
    }

    /**
     * Read UI objects for a given Properties\page.
     * @param resourceName the properties name for UI objects.
     * @return none.
     */
    public static Properties loadUIObjects(final String resourceName) {
    	Properties uiObject = new Properties();
    	String configFilePath = Reporter.getCurrentTestResult().getTestContext().getOutputDirectory();
        try {
            if (configFilePath.contains("test-output")) {
                configFilePath = configFilePath.substring(0, configFilePath.lastIndexOf("test-output"));
            } else {
                configFilePath = configFilePath.substring(0, configFilePath.lastIndexOf("target"));
            }
            configFilePath = configFilePath + "src/test/resources/objectrepo/" + resourceName;
            InputStream resourceStream = new FileInputStream(configFilePath);
            uiObject.load(resourceStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading " + configFilePath + "! " + e.getMessage());
        }
        return uiObject;
    }

    /**
     * Read UI objects for a given key value.
     * @param uiObject properties file.
     * @param keyName Key name for the Ui object.
     * @return By locator.
     */
    public static By getLocator(final Properties uiObject , final String keyName) {
    	//read the value using logical name Key
    	String locator = uiObject.getProperty(keyName);
    	//Split the value which contains locator type and locator value
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
        //Return a instance of By class based on type of locator
        if (locatorType.toLowerCase().equals("id")) {
        	return By.id(locatorValue);
        } else if (locatorType.toLowerCase().equals("name")) {
            return By.name(locatorValue);
        } else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))) {
        	return By.className(locatorValue);
        } else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))) {
        	return By.className(locatorValue);
        } else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))) {
        	return By.linkText(locatorValue);
        } else if (locatorType.toLowerCase().equals("partiallinktext")) {
        	return By.partialLinkText(locatorValue);
        } else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))) {
        	return By.cssSelector(locatorValue);
        } else if (locatorType.toLowerCase().equals("xpath")) {
        	return By.xpath(locatorValue);
        } else {
        	throw new ElementNotFoundActionException("Locator type '" + locatorType + "' not defined!!");
        }
    }
}
