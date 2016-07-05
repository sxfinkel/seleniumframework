package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Validates the on click element of an image.
 * Created by cteix4 on 20/1/2016.
 */
public class ValidateImageLink extends ElementAction {

    /** the expected link. **/
    private String expectedLink;

    /**
     * Instantiates a new action.
     */
    public ValidateImageLink() {
    }

    /**
     * Instantiates a new action setting the image web element.
     * @param element the image.
     */
    public ValidateImageLink(final By element) {
        super(element);
    }

    /**
     * Instantiates a new action setting the image web element and expected link.
     * @param element the image.
     * @param expectedLink the expected link.
     */
    public ValidateImageLink(final By element, final String expectedLink) {
        super(element);
        this.expectedLink = expectedLink;
    }

    @Override
    public void doAction(final WebDriver webDriver) {

        Preconditions.checkNotNull(expectedLink, "null expected link");

        // gets the actual link
        String actualLink = webDriver.findElement(getElement()).getAttribute("onclick");

        Assert.assertTrue(actualLink.contains(expectedLink), "The actual link [" + actualLink + "] doesn't contain the expected link [" + expectedLink + "]");
    }

    /**
     * Sets the expected link.
     * @param expectedLink the expected link.
     * @return this action.
     */
    public ValidateImageLink setExpectedLink(final String expectedLink) {
        this.expectedLink = expectedLink;
        return this;
    }
}
