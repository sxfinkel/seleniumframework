package com.aexp.wsgcat.seleniumframework;

import org.testng.ITestResult;
import org.uncommons.reportng.ReportNGUtils;

import java.util.Date;
import java.util.List;

/**
 * Adds information in the report output.
 * Created by cteix4 on 08/11/2015.
 */
public class ReportUtils extends ReportNGUtils {

    /** Key for the test info info to be printed in the report. **/
    public static final String TEST_INFO_KEY = "testInfo";

    /**
     * Gets the test output with information in the steps.
     * @param result the test result.
     * @return the test output.
     */
    public List<String> getTestOutput(final ITestResult result) {

        List<String> output = super.getTestOutput(result);

        String testInfo = (String) result.getAttribute(TEST_INFO_KEY);
        if (testInfo != null) {
            String id = String.valueOf(new Date().getTime());
            testInfo =
                    "<div onclick=\"document.getElementById('" + result.getName() + id + "').style.display=document.getElementById('"
                            + result.getName() + id  + "')"
                            + ".style.display=='none'?'block':'none';\">"
                            +       "<a href=#><b>Test Steps</b></a>"
                            +       "<div id=\"" + result.getName() + id  + "\" style=\"display: none;\">" + testInfo + "</div>"
                            +   "</div>";
            output.add(testInfo);
        }

        return output;
    }


}
