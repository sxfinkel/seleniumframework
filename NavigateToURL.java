package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.Action;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Class that implements the URL navigation action.
 * Created by cteix4 on 08/11/2015.
 */
public class NavigateToURL implements Action {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(NavigateToURL.class);

    /** The URL. **/
    private String url;

    /**
     * Instantiates new action.
     */
    public NavigateToURL() {
    }

    /**
     * Instantiates new action setting the url.
     * @param url the url.
     */
    public NavigateToURL(final String url) {
        this.url = url;
    }

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        logger.info("Navigating to " + url);
        browserSession.navigate().to(url);
    }

    /**
     * Gets the URL.
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL.
     * @param url the URL.
     * @return this action.
     */
    public NavigateToURL setUrl(final String url) {
        this.url = url;
        return this;
    }
}
