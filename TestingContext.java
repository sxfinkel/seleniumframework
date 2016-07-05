package com.aexp.wsgcat.seleniumframework;

import com.aexp.wsgcat.seleniumframework.constants.MarketCode;
import com.beust.jcommander.internal.Lists;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Loads the context for the test.
 * Created by cteix4 on 08/11/2015.
 */
public final class TestingContext {

    /** Singleton instance. **/
    private static TestingContext instance = null;

    /** Test settings properties. **/
    private static Properties envConfig = null;

    /** Market code in the test context. **/
    private static MarketCode market = null;

    /** The strategy to use: sauce labs or maven. default is maven. **/
    private static String strategy = "maven";

    /** Which browsers to execute the tests. **/
    private static List<String> browsers = Lists.newArrayList();

    /** Used for synchronize. **/
    private static final Object lock = new Object();

    /**
     * Prevents from constructing objects of this class, by declaring this private constructor.
     */
    private TestingContext() {
    }

    /**
     * Returns the instance for the test context.
     *
     * @param marketCode        the market code the test will run against.
     * @param envConfigName     the environment configuration file name.
     * @return the instance for {@Link TestingContext}.
     */
    public static TestingContext getInstance(final MarketCode marketCode, final String envConfigName) {
        TestingContext testingContext = instance;
        if (testingContext == null) {
            synchronized (lock) {    // While we were waiting for the lock, another
                testingContext = instance;        // thread may have instantiated the object.
                if (testingContext == null) {
                    testingContext = new TestingContext();
                    market = marketCode;
                    envConfig = loadProperties(envConfigName);
                    instance = testingContext;
                }
            }
        }
        return testingContext;
    }

    /**
     * Loads properties.
     * @param resourceName the file name.
     * @return the property.
     */
    private static Properties loadProperties(final String resourceName) {
        Properties properties = new Properties();
        String configFilePath = Reporter.getCurrentTestResult().getTestContext().getOutputDirectory();
        try {
            if (configFilePath.contains("test-output")) {
                configFilePath = configFilePath.substring(0, configFilePath.lastIndexOf("test-output"));
            } else {
                configFilePath = configFilePath.substring(0, configFilePath.lastIndexOf("target"));
            }
            configFilePath = configFilePath + resourceName;
            InputStream resourceStream = new FileInputStream(configFilePath);
            properties.load(resourceStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading " + configFilePath + "! " + e.getMessage());
        }
        return properties;
    }

    /**
     * Gets the market code initialization for the test.
     *
     * @return the market code.
     */
    public static MarketCode getMarketCode() {
        return market;
    }

    /**
     * Gets the value for a key in the environment configuration.
     * @param key the key.
     * @return the value.
     */
    public static String getEnvironmentValue(final String key) {
        return envConfig.getProperty(key);
    }

    /**
     * Gets the test strategy.
     * @return the test strategy.
     */
    public static String getStrategy() {
        return strategy;
    }

    /**
     * Sets the test strategy.
     * @param strategy the test strategy.
     */
    public static void setStrategy(final String strategy) {
        TestingContext.strategy = strategy;
    }

    /**
     * Checks if ths strategy is sauce labs.
     * @return true if is sauce labs, otherwise false.
     */
    public static boolean isStrategySauceLabs() {
        return strategy.toLowerCase().equals("saucelabs");
    }

    /**
     * Gets the list of browsers.
     * @return the list of browsers.
     */
    public static List<String> getBrowsers() {
        return browsers;
    }

    /**
     * Sets the list of browsers.
     * @param browsers the list of browsers.
     */
    public static void setBrowsers(final List<String> browsers) {
        TestingContext.browsers = browsers;
    }
}
