package com.aexp.wsgcat.seleniumframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class that group all action that involves an element.
 * Created by cteix4 on 08/11/2015.
 */
public abstract class ElementAction implements Action {

    /** the element identifier. **/
    private By element;

    /**
     * Instantiates a new element action.
     */
    public ElementAction() {
    }

    /**
     * Instantiates the new action setting the element.
     * @param element the element.
     */
    public ElementAction(final By element) {
        this.element = element;
    }

    /**
     * Gets the element identifier.
     * @return the element identifier.
     */
    public By getElement() {
        return element;
    }

    /**
     * Sets the element identifier.
     * @param element the element identifier.
     * @return this actions.
     */
    public ElementAction setElement(final By element) {
        this.element = element;
        return this;
    }

    /**
     * Detects is element is present in the page.
     * @param browserSession the browser session.
     * @return true if the element is present, otherwise, false.
     */
    public boolean isElementPresent(final WebDriver browserSession) {
        try {
            return ((browserSession.findElements(getElement()).size() > 0)
                    && (browserSession.findElements(getElement()).get(0).isDisplayed()));
        } catch (RuntimeException e) {
            return false;
        }
    }

}
