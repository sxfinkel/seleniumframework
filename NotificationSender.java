package com.aexp.wsgcat.seleniumframework;

import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Class responsible to send an email notification.
 * Created by vponn1 on 10/11/2015.
 */
public class NotificationSender {

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(NotificationSender.class);

    /**
     * Sends an email notification.
     * @param suites the list of suites.
     */
    public static void sendEmailNotification(final List<ISuite> suites) {
        try {

            final String username = TestingContext.getEnvironmentValue("email_notification_username");
            final String password = TestingContext.getEnvironmentValue("email_notification_password");
            String from = TestingContext.getEnvironmentValue("email_notification_from");
            String to = TestingContext.getEnvironmentValue("email_notification_to");
            String subject = TestingContext.getEnvironmentValue("email_notification_subject");

            Preconditions.checkNotNull(username, "Username not configured to send email notification!");
            Preconditions.checkNotNull(password, "Password not configured to send email notification!");
            Preconditions.checkNotNull(from, "From not configured to send email notification!");
            Preconditions.checkNotNull(username, "To not configured to send email notification!");

            // AEXP configurations
            String smtp = "mail.aexp.com";
            String port = "587";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtp);
            props.put("mail.smtp.port", port);

            //Authentication to SMTP server
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            //Create email structure
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toArray = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, toArray);

            if (subject == null || subject.isEmpty()) {
                subject = "Test Report";
            }
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            String emailContent = formatEmailNotification(suites);
            messageBodyPart.setText(emailContent);
            messageBodyPart.setContent(emailContent, "text/html");

            // Create a multi part message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception e) {
            logger.info("Notification will not be sent.");
        }
    }

    /**
     * Formats the email to send.
     * @param suites the list of suites.
     * @return the email content.
     */
    private static String formatEmailNotification(final List<ISuite> suites) {
        StringBuffer emailContent = new StringBuffer();
        String outputDir = "";

        for (ISuite suite : suites) {

            emailContent.append("<h2> Suite: " + suite.getName() + "</h2> \n\n");

            Map<String, ISuiteResult> suiteResultMap = suite.getResults();

            Set<String> testKeys = suiteResultMap.keySet();
            for (String key : testKeys) {
                ITestContext testContext = suiteResultMap.get(key).getTestContext();

                emailContent.append("<hr><b>" + testContext.getName() + "</b><br>");

                // Failed tests:
                IResultMap resultMapFailed = testContext.getFailedTests();
                // Skipped tests:
                IResultMap resultMapSkipped = testContext.getSkippedTests();
                // Passed tests:
                IResultMap resultMapPassed = testContext.getPassedTests();

                //Execution Summary
                emailContent.append("<i>Tests run: " + testContext.getAllTestMethods().length + ", ");
                emailContent.append("Failures: " + resultMapFailed.size() + ", ");
                emailContent.append("Skipped: " + resultMapSkipped.size() + ", ");
                emailContent.append("Succeed: " + resultMapPassed.size() + "</i><br>");

                if (resultMapFailed.size() > 0) {
                    emailContent.append("Failed Tests: " + formatResultMap(resultMapFailed) + "<br>");
                }

                if (resultMapSkipped.size() > 0) {
                    emailContent.append("Skipped Tests: " + formatResultMap(resultMapSkipped) + "<br>");
                }

                if (resultMapPassed.size() > 0) {
                    emailContent.append("Passed Tests: " + formatResultMap(resultMapPassed) + "<br>");
                }
            }
            emailContent.append("<hr>");
            outputDir = suite.getOutputDirectory();
            outputDir = outputDir.substring(0, outputDir.lastIndexOf('\\')) + "\\html\\index.html";
        }
        emailContent.append("<br><br> Full test report: <a href=\"" + outputDir + "\">" + outputDir + "</a>");
        return emailContent.toString();
    }

    /**
     * Format a result map to a list of test name.
     * @param resultMap the result map.
     * @return test list name.
     */
    private static String formatResultMap(final IResultMap resultMap) {
        StringBuffer formatResultMap = new StringBuffer();
        Set<ITestResult> testResults = resultMap.getAllResults();

        boolean passed = false;
        for (ITestResult testResult : testResults) {
            if (passed) {
                formatResultMap.append(", ");
            }
            formatResultMap.append(testResult.getName());
            passed = true;
        }
        return formatResultMap.toString();
    }

    /**
     * Constructor for a utility class.
     */
    private NotificationSender() {
    }
}
