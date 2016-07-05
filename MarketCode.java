package com.aexp.wsgcat.seleniumframework.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the different market code supported for WSP.
 * Created by cteix4 on 08/11/2015.
 */
public enum MarketCode {

    /** International market code. **/
    INTERNATIONAL("INTER"),
    /** United States code. **/
    UNITED_STATES("US");

    /** Market code. **/
    private String code;

    /**
     * Market code.
     * @param code the market code.
     */
    MarketCode(final String code) {
        this.code = code;
    }

    /** Map to save the enum by the code. **/
    private static Map<String, MarketCode> map = new HashMap<String, MarketCode>();

    /**
     * Saves the enum by using the code as the key.
     */
    static {
        for (MarketCode marketCode : MarketCode.values()) {
            map.put(marketCode.code, marketCode);
        }
    }

    /**
     * Gets the enum by the code.
     * @param code the code.
     * @return the enum.
     */
    public static MarketCode getByCode(final String code) {
        return map.get(code);
    }
}
