package com.aexp.wsgcat.seleniumframework.trackingservice;

import org.apache.log4j.Logger;
/**
 * Utility to track the saves captured and push it to http call
 * Created by bvenkatr on 01/21/2015.
 */
public class TrackingProfile {
	/** Logger. **/
    public static final Logger logger = Logger.getLogger(TrackingProfile.class);
    /** masterProjectId declaration. **/
	private String masterProjectId;
	/** subProjectId declaration. **/
	private String subProjectId;
	/** adsId declaration. **/
	private String adsId;
	/** applicationId declaration. **/
	private String applicationId;
	/** applicationName declaration. **/
	private String applicationName;
	/** scriptId declaration. **/
	private String scriptId;
	/** scriptDevelopmentDate declaration. **/
	private String scriptDevelopmentDate;
	/** scriptDescription declaration. **/
	private String scriptDescription;
	/** category declaration. **/
	private String category;
	/** platform declaration. **/
	private String platform;
	/** runDate declaration. **/
	private String runDate;
	/** manualEffort declaration. **/
	private String manualEffort;
	/** automatedEffort declaration. **/
	private String automatedEffort;
	/** noOfPass declaration. **/
	private int noOfPass = 0;
	/** noOfFail declaration. **/
	private int noOfFail = 0;
	/** minsSaved declaration. **/
	private int minsSaved = 0;
	/** startTime declaration. **/
	private long startTime;
	/** endTime declaration. **/
	private long endTime;
	/**
     * get the master project ID.
     * @return String masterproject.
     */
	public String getMasterProjectId() {
		return masterProjectId;
	}
	/**
     * sets the  master project ID.
     * @param masterProjectId id for master project.
     */
	public void setMasterProjectId(final String masterProjectId) {
		this.masterProjectId = masterProjectId;
	}
	/**
     * get the Sub project ID.
     * @return String Subproject.
     */
	public String getSubProjectId() {
		return subProjectId;
	}
	/**
     * sets the  Sub project ID.
     * @param subProjectId id for Sub project.
     */
	public void setSubProjectId(final String subProjectId) {
		this.subProjectId = subProjectId;
	}
	/**
     * get the Ads ID.
     * @return String adsId.
     */
	public String getAdsId() {
		return adsId;
	}
	/**
     * sets the  Ads ID.
     * @param adsId id for AdsId.
     */
	public void setAdsId(final String adsId) {
		this.adsId = adsId;
	}
	/**
     * get the applicationId.
     * @return String applicationId.
     */
	public String getApplicationId() {
		return applicationId;
	}
	/**
     * sets the  applicationId.
     * @param applicationId id for application.
     */
	public void setApplicationId(final String applicationId) {
		this.applicationId = applicationId;
	}
	/**
     * get the applicationName.
     * @return String applicationName.
     */
	public String getApplicationName() {
		return applicationName;
	}
	/**
     * sets the  applicationName.
     * @param applicationName name for applicationName.
     */
	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
	}
	/**
     * get the scriptId.
     * @return String scriptId.
     */
	public String getScriptId() {
		return scriptId;
	}
	/**
     * sets the  scriptId.
     * @param scriptId id for scriptId.
     */
	public void setScriptId(final String scriptId) {
		this.scriptId = scriptId;
	}
	/**
     * get the scriptDevelopmentDate.
     * @return String scriptDevelopmentDate.
     */
	public String getScriptDevelopmentDate() {
		return scriptDevelopmentDate;
	}
	/**
     * sets the  scriptDevelopmentDate.
     * @param scriptDevelopmentDate id for scriptDevelopmentDate.
     */
	public void setScriptDevelopmentDate(final String scriptDevelopmentDate) {
		this.scriptDevelopmentDate = scriptDevelopmentDate;
	}
	/**
     * get the scriptDescription.
     * @return String scriptDescription.
     */
	public String getScriptDescription() {
		return scriptDescription;
	}
	/**
     * sets the  scriptDescription.
     * @param scriptDescription id for scriptDescription.
     */
	public void setScriptDescription(final String scriptDescription) {
		this.scriptDescription = scriptDescription;
	}
	/**
     * get the category.
     * @return String category.
     */
	public String getCategory() {
			return category;
	}
	/**
     * sets the  category.
     * @param category id for category.
     */
	public void setCategory(final String category) {
		this.category = category;
	}
	/**
     * get the platform.
     * @return String platform.
     */
	public String getPlatform() {
		return platform;
	}
	/**
     * sets the  platform.
     * @param platform id for platform.
     */
	public void setPlatform(final String platform) {
		this.platform = platform;
	}
	/**
     * get the runDate.
     * @return String runDate.
     */
	public String getRunDate() {
		return runDate;
	}
	/**
     * sets the  runDate.
     * @param runDate id for runDate.
     */
	public void setRunDate(final String runDate) {
		this.runDate = runDate;
	}
	/**
     * get the manualEffort.
     * @return String manualEffort.
     */
	public String getManualEffort() {
		return manualEffort;
	}
	/**
     * sets the  manualEffort.
     * @param manualEffort id for manualEffort.
     */
	public void setManualEffort(final String manualEffort) {
		this.manualEffort = manualEffort;
	}
	/**
     * get the automatedEffort.
     * @return String automatedEffort.
     */
	public String getAutomatedEffort() {
		return automatedEffort;
	}
	/**
     * sets the  automatedEffort.
     * @param automatedEffort id for automatedEffort.
     */
	public void setAutomatedEffort(final String automatedEffort) {
		this.automatedEffort = automatedEffort;
	}
	/**
     * get the noOfPass.
     * @return String noOfPass.
     */
	public int getNoOfPass() {
		return noOfPass;
	}
	/**
     * sets the  noOfPass.
     * @param noOfPass id for noOfPass.
     */
	public void setNoOfPass(final int noOfPass) {
		this.noOfPass = noOfPass;
	}
	/**
     * get the noOfFail.
     * @return String noOfFail.
     */
	public int getNoOfFail() {
		return noOfFail;
	}
	/**
     * sets the  noOfFail.
     * @param noOfFail id for noOfFail.
     */
	public void setNoOfFail(final int noOfFail) {
		this.noOfFail = noOfFail;
	}
	/**
     * get the minsSaved.
     * @return String minsSaved.
     */
	public int getMinsSaved() {
		return minsSaved;
	}
	/**
     * sets the  minsSaved.
     * @param minsSaved id for minsSaved.
     */
	public void setMinsSaved(final int minsSaved) {
		this.minsSaved = noOfPass * (Integer.valueOf(manualEffort) - Integer.valueOf(automatedEffort));
	}
	/**
     * get the startTime.
     * @return String startTime.
     */
	public long getStartTime() {
		return startTime;
	}
	/**
     * sets the  startTime.
     * @param startTime id for startTime.
     */
	public void setStartTime(final long startTime) {
		this.startTime = startTime;
	}
	/**
     * get the endTime.
     * @return String endTime.
     */
	public long getEndTime() {
		return endTime;
	}
	/**
     * sets the  endTime.
     * @param endTime id for endTime.
     */
	public void setEndTime(final long endTime) {
		this.endTime = endTime;
	}
}
