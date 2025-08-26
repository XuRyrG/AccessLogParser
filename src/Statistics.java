import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    private Set<String> existingPages;
    private Map<String, Integer> countOs;
    private Set<String> nonExistingPages;
    private Map<String, Integer> browserStatistics;
    private int userVisits;
    private int errorRequests;
    private Set<String> uniqueUserIP;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.existingPages = new HashSet<>();
        this.countOs = new HashMap<>();
        this.nonExistingPages = new HashSet<>();
        this.browserStatistics = new HashMap<>();
        this.userVisits = 0;
        this.errorRequests = 0;
        this.uniqueUserIP = new HashSet<>();

    }

    public void addEntry(LogEntry entry){
        this.totalTraffic += entry.getBytesSent();
        if(minTime == null || entry.getDateTime().isBefore(minTime)){
            this.minTime = entry.getDateTime();
        }
        if(maxTime == null || entry.getDateTime().isAfter(maxTime)){
            this.maxTime = entry.getDateTime();
        }

        if (entry.getStatusCode()==200) {
            existingPages.add(entry.getPath());
        }
        if (entry.getStatusCode() == 404) {
            nonExistingPages.add(entry.getPath());
        }

        String browser=entry.getUserAgent().getBrowser();
        browserStatistics.put(browser,browserStatistics.getOrDefault(browser,0)+1);


        String os= entry.getUserAgent().getOperatingSystem();
        countOs.put(os,countOs.getOrDefault(os,0)+1);

        boolean isBot = entry.getUserAgent().isBot();
        if (!isBot) {
            userVisits++;
            uniqueUserIP.add(entry.getIpAddress());
        }


        int statusCode = entry.getStatusCode();
        if (statusCode >= 400 && statusCode < 600) {
            errorRequests++;
        }

    }
    public double getTrafficRate() {
        if (minTime == null || maxTime == null) {
            return 0;
        }

        double hours = java.time.Duration.between(minTime, maxTime).toHours();
        if (hours == 0) {
            hours = 1;
        }

        return (double) totalTraffic / hours;
    }

    public Set<String> getExistingPages() {
        return new HashSet<>(existingPages);
    }

    public Set<String> getNonExistingPages() {
        return new HashSet<>(nonExistingPages);
    }


    public Map<String,Double> getOsStatistics() {
        Map<String,Double> res=new HashMap<>();
        int total=0;
        for (int count:countOs.values()) {
            total+=count;
        }
        if (total==0) {
            return res;
        }
        for (String os:countOs.keySet()) {
            int count=countOs.get(os);
            double fraction=(double) count/total;
            res.put(os,fraction);
        }
        return res;
    }

    public Map<String,Double> getBrowserStatistics() {
        Map<String,Double> res=new HashMap<>();
        int total=0;
        for (int count:browserStatistics.values()) {
            total+=count;
        }
        if (total==0) {
            return res;
        }

        for (String browser:browserStatistics.keySet()) {
            int count=browserStatistics.get(browser);
            double fraction=(double) count/total;
            res.put(browser,fraction);
        }
        return res;
    }

    public double getAverageVisitsPerHour() {
        if (minTime == null || maxTime == null) {
            return 0;
        }

        double hours = java.time.Duration.between(minTime, maxTime).toHours();
        if (hours == 0) {
            hours = 1;
        }

        return (double) userVisits / hours;
    }

    public double getAverageErrorsPerHour() {
        if (minTime == null || maxTime == null || errorRequests == 0) {
            return 0;
        }

        double hours = java.time.Duration.between(minTime, maxTime).toHours();
        if (hours == 0) {
            hours = 1;
        }

        return (double) errorRequests / hours;
    }
    public double getAverageVisitsPerUser() {
        if (uniqueUserIP.isEmpty()) {
            return 0;
        }

        return (double) userVisits / uniqueUserIP.size();
    }
}