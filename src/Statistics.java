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


    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.existingPages = new HashSet<>();
        this.countOs = new HashMap<>();

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


        String os= entry.getUserAgent().getOperatingSystem();
        countOs.put(os,countOs.getOrDefault(os,0)+1);
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
}