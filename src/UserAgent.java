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
        if (userAgent.contains("Windows")) return "Windows";
        if (userAgent.contains("Mac")) return "macOS";
        if (userAgent.contains("Linux")) return "Linux";

        return "Other";
    }

    private String parseBrowser(String userAgent) {
        userAgent = userAgent.toLowerCase();

        if (userAgent.contains("Edge")) return "Edge";
        if (userAgent.contains("Firefox")) return "Firefox";
        if (userAgent.contains("Chrome")) return "Chrome";
        if (userAgent.contains("Safari")) return "Safari";
        if (userAgent.contains("Opera")) return "Opera";

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