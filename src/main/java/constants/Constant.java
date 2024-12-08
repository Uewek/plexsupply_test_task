package constants;

import java.time.Duration;

/**
 * Class with project constants
 */
public class Constant {
    /**
     * Internal class with timeout variables
     */
    public static class TimeoutVariables {
        public static final int IMPLICIT_WAIT = 4;
        public static final int EXCLICIT_WAIT = 10;
    }

    /**
     * Internal class with urls assigned to Allo.ua
     */
    /**
     * Internal class with urls assigned to plexsupply.com
     */
    public static class PlexSupplyUrls {
        public static final String PLEXSUPPLY_HOME_PAGE_URL = "https://plexsupply.com/";
        public static final String PLEXSUPPLY_CHECKOUT_PAGE_URL = "https://plexsupply.com/checkout/";
    }

    /**
     * Internal class with all search queries
     */
    public static class Filters {
        public static final String E_A_R = "E-A-R";
        public static final String BOX = "BOX";
    }

    /**
     * Internal class with categories names
     */
    public static class Categories {
        public static final String HEALTH = "Health & Beauty";
        public static final String EAR_CARE = "Ear Care";
        public static final String EARPLUGS = "Earplugs";
        public static final String PROMO = "Promo Goods";
        public static final String COFFEE_PROMO = "Coffee Promo";
    }

    /**
     * Array with browser allowed combinations
     */
    public static class BrowserCombinations {
        public static final String PLATFORM_AND_BROWSER = "linux_chrome";
        public static final String[] OS_AND_BROWSER_COMBINATIONS = {"linux_chrome", "windows_chrome", "linux_firefox", "windows_firefox"};
    }

    /**
     * Error messages for project
     */
    public static class ErrorMessages{
        public static final String WRONG_OS_BROWSER_COMBINATION_MESSAGE = " is not allowed combination. Now allowed only (linux_chrome, windows_chrome, linux_firefox, windows_firefox) combinations";
    }
}
