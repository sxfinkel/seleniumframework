package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Validates the presence of an image.
 * Created by cteix4 on 12/1/2016.
 */
public class ValidateImage extends ElementAction {

    /**
     * Instantiates a new action.
     */
    public ValidateImage() {
    }

    /**
     * Instantiates a new action setting the image web element.
     * @param element the image.
     */
    public ValidateImage(final By element) {
        super(element);
    }

    @Override
    public void doAction(final WebDriver webDriver) {
        // Gets the image
        WebElement imageFile = webDriver.findElement(getElement());

        // Checks the image presence
        Boolean isImagePresent = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth "
                + "!= \"undefined\" && arguments[0].naturalWidth > 0", imageFile);

        Assert.assertTrue(isImagePresent, "Image [" + getElement() + "] not displayed!");
    }
}
