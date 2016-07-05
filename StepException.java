package com.aexp.wsgcat.seleniumframework;

/**
 * Unexpected exceptions in the step.
 * Created by cteix4 on 08/11/2015.
 */
public class StepException extends RuntimeException {

    /**
	 * serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates new step exception.
     * @param s the error message.
     * @param throwable the exception.
     */
    public StepException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

    /**
     * Instantiates new step exception.
     * @param s the error message.
     */
    public StepException(final String s) {
        super(s);
    }
}
