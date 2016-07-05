package com.aexp.wsgcat.seleniumframework.constants;

/**
 * Created by cteix4 on 9/1/2015.
 */
public final class Constants {

    /** Time out in seconds used in the steps for waiting for an element. **/
    public static final int MAXIMUM_WAIT_TIME = 60;

    /** Wait Time in milliseconds used in the steps for waiting for an element. **/
    public static final int SYNC_WAIT_TIME = 2000;

    /** Screenshot sub folder. **/
    public static final String SCREENSHOT_SUBFOLDER = "screenshot";
    /**
     * Prevents from constructing objects of this class, by declaring this private constructor.
     */
    private Constants() {
    }
}

