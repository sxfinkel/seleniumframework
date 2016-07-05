package com.aexp.wsgcat.seleniumframework;

import com.aexp.wsgcat.seleniumframework.constants.MarketCode;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Preconditions;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Base test to be extended for all tests.
 * Created by cteix4 on 08/11/2015.
 */
public class BaseTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    /** Max wait time. **/
    private static final int MAXIMUM_WAIT_TIME = 60;

    /** Logger. **/
    public static final Logger logger = Logger.getLogger(BaseTest.class);

    /** Username. **/
    private String username = System.getenv("SAUCE_USER_NAME") != null ? System.getenv("SAUCE_USER_NAME") : System.getenv("SAUCE_USERNAME");

    /** Access Key. **/
    private String accessKey = System.getenv("SAUCE_API_KEY") != null ? System.getenv("SAUCE_API_KEY") : System.getenv("SAUCE_ACCESS_KEY");

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accessKey);

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * Test suite sets the test context and fetch the suite from the XML file.
     *
     * @param strategy      the test strategy.
     * @param browsers      which browsers to run the test (ie, firefox, chrome).
     * @param marketCode    the market CODE.
     * @param environment   the test environment.
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({ "strategy", "browsers", "marketCode", "environment" })
    public void setUP(@Optional("maven") final String strategy,
                      @Optional("internetExplorer") final String browsers,
                      @Optional("US") final String marketCode,
                      @Optional("src/test/config/qa.properties") final String environment) {
        Preconditions.checkNotNull(environment, "specify the configuration for the environment.");
        TestingContext.getInstance(MarketCode.getByCode(marketCode), environment);

        if (strategy != null && !strategy.isEmpty()) {
            TestingContext.setStrategy(strategy);
        }

        TestingContext.setBrowsers(Arrays.asList(browsers.toLowerCase().split("\\s*,\\s*")));
    }

    /**
     * Gets the Internet explorer driver to be used in the test.
     * @return the setBrowserSession.
     */
    public WebDriver getInternetExplorerDriver() {
        File file = new File("src/test/resources/webdrivers/IEDriverServer.exe");
        logger.info("Opening IE driver from Maven");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, false);
        caps.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
        caps.setCapability("nativeEvents", false);

        WebDriver driver = new InternetExplorerDriver(caps);
        driver.manage().timeouts().implicitlyWait(MAXIMUM_WAIT_TIME, TimeUnit.SECONDS);

        driver.manage().timeouts().setScriptTimeout(MAXIMUM_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Gets the firefox driver to be used in the test.
     * @return the setBrowserSession.
     */
    public WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    /**
     * Gets the chrome driver to be used in the test.
     * @return the setBrowserSession.
     */
    public WebDriver getChromeDriver() {
        File file = new File("src/test/resources/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(MAXIMUM_WAIT_TIME, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * Data Provider that provides the browser session.
     * @param   testMethod  the test method.
     * @return the data provider.
     * @throws  MalformedURLException if an error occurs.
     */
    @DataProvider(name = "driverDataProvider")
    public Object[][] driverDataProvider(final Method testMethod) throws MalformedURLException {

        List<WebDriver> webDrivers = Lists.newArrayList();

        // Sauce Labs web drivers instantiation
       if (TestingContext.isStrategySauceLabs()) {
            logger.info("Starting sauceDriverDataProvider...");
            logger.debug("Data provider will generate the drivers to be tested...");

            if (TestingContext.getBrowsers().contains("internetexplorer")) {
                webDrivers.add(createDriver("internet explorer", "11", "Windows 8.1", testMethod.getName()));
            }
            if (TestingContext.getBrowsers().contains("firefox")) {
                webDrivers.add(createDriver("firefox", "36", "Windows 7", testMethod.getName()));
            }
            if (TestingContext.getBrowsers().contains("chrome")) {
                webDrivers.add(createDriver("chrome", "41", "Windows XP", testMethod.getName()));
            }
           if (TestingContext.getBrowsers().contains("safari")) {
               webDrivers.add(createDriver("safari", "7", "OS X 10.9", testMethod.getName()));
           }
        // Local web driver instantiation
        } else {
           if (TestingContext.getBrowsers().contains("internetexplorer")) {
               webDrivers.add(getInternetExplorerDriver());
           }
           if (TestingContext.getBrowsers().contains("firefox")) {
               webDrivers.add(getFirefoxDriver());
           }
           if (TestingContext.getBrowsers().contains("chrome")) {
               webDrivers.add(getChromeDriver());
           }
        }
        return convertListToObjectArray(webDrivers);
    }

    /**
     * Converts a list of web drivers in an object array.
     * @param webDrivers the drive list.
     * @return the object array.
     */
    private Object[][] convertListToObjectArray(final List<WebDriver> webDrivers) {
        Object [][] objArray = new Object[webDrivers.size()][];

        for (int i = 0; i < webDrivers.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = webDrivers.get(i);
        }
        return objArray;
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param   browser     Represents the browser to be used as part of the test run.
     * @param   version     Represents the version of the browser to be used as part of the test run.
     * @param   os          Represents the operating system to be used as part of the test run.
     * @param   methodName  Test method name.
     * @return  the driver created.
     * @throws MalformedURLException if an error occurs parsing the url
     */
    private WebDriver createDriver(final String browser, final String version, final String os, final String methodName) throws MalformedURLException {
        try {
            logger.debug("Driver creation for browser: " + browser + ", " + version + ", " + os + "...");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
            if (version != null) {
                capabilities.setCapability(CapabilityType.VERSION, version);
            }
            capabilities.setCapability(CapabilityType.PLATFORM, os);

            String jobName = methodName + '_' + os + '_' + browser + '_' + version;
            logger.debug("Job nam: " + jobName);

            capabilities.setCapability("name", jobName);
            webDriver.set(new RemoteWebDriver(
                    new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                    capabilities));
            String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
            sessionId.set(id);

            String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", id, jobName);
            System.out.println(message);
            return webDriver.get();
        } catch (RuntimeException e) {
            logger.error("Error creating web driver. " + e);
            logger.error(ExceptionUtils.getFullStackTrace(e));
            throw e;
        }
    }

    /**
     * Gets the web driver.
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        System.out.println("WebDriver" + webDriver.get());
        return webDriver.get();
    }

    /**
     *  Gets the authentication.
     * @return the {@link SauceOnDemandAuthentication} instance containing the Sauce username/access key
     */
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    /**
     * Gets the session id.
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Quits the browser.
     * @param result test result.
     */
    @AfterMethod
    public void tearDown(final ITestResult result) {
        WebDriver driver = (WebDriver) result.getAttribute(BaseHTMLReporter.DRIVER_ATTRIBUTE);
        if (driver != null) {
        	logger.info("Closing driver from Maven");
            driver.quit();
        }
    }
}
