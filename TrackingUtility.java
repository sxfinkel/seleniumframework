package com.aexp.wsgcat.seleniumframework.trackingservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Utility to track the saves captured and push it to http call
 * Created by bvenkatr on 01/21/2015.
 */
public class TrackingUtility {
	/** Logger. **/
    public static final Logger logger = Logger.getLogger(TrackingUtility.class);
    /** prevents instantiate. **/
    private TrackingUtility() {
    	//nothing to do
    }
    /**
     * tracking mechanism building URL.
     * @param profile profile object pojo.
     */
    public static void trackingMechanism(final TrackingProfile profile) {
    	/** URL for capturing metrics. **/
        String url = "http://onegsqoportal-e1.app.aexp.com/AutomationTracking/AutomationTrackingService.svc/RecordMetrics?";
        /**constant for time calculation. **/
        final int timeDiv = 1000;
        /**constant for time calculation. **/
        final int seconds = 60;
        /**constant for automated effort calculation. **/
        final int automatedEffort = 60;
    	/**Setting ADS ID. **/
        if (profile.getAdsId() == null || profile.getAdsId().equals("")) {
        	profile.setAdsId(System.getProperty("user.name"));
        }
        logger.info("ADS Id is :" + profile.getAdsId());
        /**setting script development date. **/
        Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		//profile.setScriptDevelopmentDate(format.format(currentDate));
		profile.setScriptDevelopmentDate("01/01/2015");
		 /**setting script run date. **/
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String formattedDate = format1.format(currentDate);
		formattedDate = formattedDate.replace("/", "");
		formattedDate = formattedDate.replace(":", "");
		formattedDate = formattedDate.replace(" ", "");
		profile.setRunDate(format1.format(currentDate));
		 /**setting automated effort. **/
		double startTime = profile.getStartTime();
		double endTime = profile.getEndTime();
		double executionTimeInMinutes = (((endTime - startTime) / (timeDiv * seconds)) % seconds);
		// ( (profile.getEndTime() - profile.getStartTime() ) / ( 1000 * 60 ) % 60 );
		profile.setAutomatedEffort(String.valueOf(executionTimeInMinutes / (profile.getNoOfPass() + profile.getNoOfFail() * automatedEffort)));
		 /**appending URLs with multiple values. **/
		String name = "SubProject_ID";
		if (profile.getSubProjectId() == null) {
			profile.setSubProjectId("Test");
		}
		url = addParameter(url, name, profile.getSubProjectId());
		name = "Ads_ID";
		url = addParameter(url, name, profile.getAdsId());
		name = "Script_ID";
		url = addParameter(url, name, profile.getScriptId());
		name = "ScriptDevelopmentDate";
		url = addParameter(url, name, profile.getScriptDevelopmentDate());
		name = "ScriptDesc";
		url = addParameter(url, name, profile.getScriptDescription());
		name = "Category";
		url = addParameter(url, name, profile.getCategory());
		name = "Tool";
		url = addParameter(url, name, profile.getApplicationId());
		name = "RunDate";
		url = addParameter(url, name, profile.getRunDate());
		name = "ManualEffort";
		url = addParameter(url, name, profile.getManualEffort());
		name = "AutomatedEffort";
		url = addParameter(url, name, profile.getAutomatedEffort());
		name = "NoOfPass";
		String noOfPass = String.valueOf(profile.getNoOfPass());
		url = addParameter(url, name, noOfPass);
		name = "NoOfFail";
		String noOfFail = String.valueOf(profile.getNoOfFail());
		url = addParameter(url, name, noOfFail);
		logger.info("Final URL is:" + url);
		if (!profile.getAdsId().equalsIgnoreCase("SYSTEM")) {
			invokeURL(url);
		}
    }
		/**
	     * invokes the builded URL.
	     * @param url url string to be invoked.
	     */
		private static void invokeURL(final String url) {
			try {
				StringBuilder response = new StringBuilder();
				//url = encodeUrl(url);
				URL urlRequest = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
			    conn.setDoOutput(true);
			    conn.setDoInput(true);
			    conn.setRequestMethod("GET");
			    InputStream stream = conn.getInputStream();
			    BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			    String str = "";
			    while ((str = in.readLine()) != null) {
			        response.append(str);
			    }
			    in.close();
			    logger.info("response url" + response.toString());
			} catch (Exception ex) {
				logger.error("erorr in invoking Tracking metrics URL", ex);
			}
		}
		/**
	     * adds paramter to the URL to build it.
	     * @param url url string to be invoked.
	     * @param name of the paramter to be added.
	     * @param value of the paramter to be added.
	     * @return added url string.
	     */
		public static String addParameter(final String url, final String name, final String value) {
		    int qpos = url.indexOf('?');
		    int hpos = url.indexOf('#');
		    char sep = qpos == -1 ? '?' : '&';
		    String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
		    return hpos == -1 ? url + seg : url.substring(0, hpos) + seg
		        + url.substring(hpos);
		  }

		 /**
		   * The same behaviour as Web.escapeUrl, only without the "funky encoding" of
		   * the characters ? and ; (uses JDK URLEncoder directly).
		   * @param url The string to encode.
		   * @return <code>toencode</code> fully escaped using URL rules.
		   */
		  public static String encodeUrl(final String url) {
		    try {
		      return URLEncoder.encode(url, "UTF-8");
		    } catch (UnsupportedEncodingException uee) {
		      throw new IllegalArgumentException(uee);
		    }
		  }
}
