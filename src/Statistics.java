import java.time.LocalDateTime;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry o){
        this.totalTraffic += o.getBytesSent();
        if(minTime == null || o.getDateTime().isBefore(minTime)){
            this.minTime = o.getDateTime();
        }
        if(maxTime == null || o.getDateTime().isAfter(maxTime)){
            this.maxTime = o.getDateTime();
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

}