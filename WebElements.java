package com.aexp.qa.nameofyourproject.constants;

import org.openqa.selenium.By;

/**
 * Elements used to identify elements in Steps.
 */
public final class WebElements {

    /********************
     * LIST ALL Elements. *
     ********************/

    /**
     * FRAMES
     */

    /**
     * LINKS
     */

    /** list of links elements here. **/


    /**
     * BUTTONS
     */

    /** Proceed button on google search. **/
    public static final By BUTTON_GOOGLE_SEARCH = By.xpath("//div[@id='sblsbb']/button");


    /**
     * FIELDS to fill
     */

    /** Google search field in the home page. **/
    public static final By FIELD_GOOGLE_SEARCH = By.xpath("//div[@id='gs_lc0']/input");


    /**
     * RADIO to select
     */

    /** List of radio buttons. **/


    /**
     * Prevents from constructing objects of this class, by declaring this private constructor.
     */
    private WebElements() {
    }
}
