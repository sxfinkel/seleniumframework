package com.aexp.wsgcat.seleniumframework;

/**
 * Framework throws this exception when an element is not found.
 * Created by cteix4 on 08/11/2015.
 */
public class ElementNotFoundActionException extends RuntimeException {

    /**
	 * serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Defines the exception message.
     * @param s exception message
     */
    public ElementNotFoundActionException(final String s) {
        super(s);
    }
}
