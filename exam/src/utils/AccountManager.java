package utils;

public class AccountManager {
    private static String USERNAME;
    private static String PASSWORD;
    private static String EMAIL;

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        AccountManager.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        AccountManager.PASSWORD = PASSWORD;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        AccountManager.EMAIL = EMAIL;
    }
}
