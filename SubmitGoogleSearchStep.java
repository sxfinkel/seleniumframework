package com.aexp.qa.nameofyourproject.steps;

import com.aexp.qa.nameofyourproject.constants.WebElements;
import com.aexp.qa.testframework.BaseStep;
import com.aexp.qa.testframework.actions.ClickOnElement;
import com.aexp.qa.testframework.actions.SendKeysToElement;
import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

public class SubmitGoogleSearchStep extends BaseStep{
	
	/** Logger. **/
	public static final Logger logger = Logger.getLogger(SubmitGoogleSearchStep.class);
	
	/** String to search. **/
	private String searchString;
	
	@Override
	public void checkPreconditions() {
		Preconditions.checkNotNull(searchString, "null searchString");
	}

	@Override
	public void doExecute(WebDriver browserSession) {
		// Fill the string to search
		new SendKeysToElement(WebElements.FIELD_GOOGLE_SEARCH, searchString).doAction(browserSession);
		
		// Confirm search
		new ClickOnElement(WebElements.BUTTON_GOOGLE_SEARCH).doAction(browserSession);
	}

	@Override
	public void validateOutput(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateStepExecution(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the string.
	 * @return the string to be searched.
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * Sets the string to be searched.
	 * @param searchString the string.
	 * @return this class.
	 */
	public SubmitGoogleSearchStep setSearchString(String searchString) {
		this.searchString = searchString;
		return this;
	}
	
	

}
