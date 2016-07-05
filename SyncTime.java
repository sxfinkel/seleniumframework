package com.aexp.wsgcat.seleniumframework.actions;

import com.aexp.wsgcat.seleniumframework.ElementAction;
import com.aexp.wsgcat.seleniumframework.constants.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Waits for sync time.
 * Created by vponn1 on 08/11/2015.
 */
public class SyncTime extends ElementAction {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(SyncTime.class);

    /**
     * Action implementation.
     * @param browserSession the browser session.
     */
    public void doAction(final WebDriver browserSession) {
        logger.info("Waiting for Sync time");
		try {
			Thread.sleep(Constants.SYNC_WAIT_TIME);
		} catch (InterruptedException e) {
			logger.error("Error waiting for SyncTime..." + e.getMessage());
		}
    }
}
