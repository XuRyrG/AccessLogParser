public class UserAgent {
    private final String operatingSystem;
    private final String browser;
    private final boolean isBot;

    public UserAgent(String userAgentString) {
        this.operatingSystem = parseOperatingSystem(userAgentString);
        this.browser = parseBrowser(userAgentString);
        this.isBot = userAgentString.toLowerCase().contains("bot");
    }

    private String parseOperatingSystem(String userAgent) {
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("windows")) return "Windows";
        if (userAgent.contains("mac")) return "macOS";
        if (userAgent.contains("linux")) return "Linux";

        return "Other";
    }

    private String parseBrowser(String userAgent) {
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("edge")) return "Edge";
        if (userAgent.contains("firefox")) return "Firefox";
        if (userAgent.contains("chrome")) return "Chrome";
        if (userAgent.contains("safari")) return "Safari";
        if (userAgent.contains("opera")) return "Opera";

        return "Other";
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }
    public boolean isBot() {
        return isBot;
    }
}