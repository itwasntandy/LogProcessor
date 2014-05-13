package sh.bash.log;


public class LogEntry {
    /* within LogFields we are storing 3 fields for now
    This may be extended later if we decide to produce stats on other fields too.
     */
    private final int respCode, respSize, respTime;


    public LogEntry(int newRespCode, int newRespSize, int newRespTime) {
        respCode = newRespCode;
        respSize = newRespSize;
        respTime = newRespTime;
    }

    public int getRespCode() {
        return respCode;
    }

    public int getRespSize() {
        return respSize;
    }

    public int getRespTime() {
        return respTime;
    }


    @Override
    public String toString() {
        return "LogEntry{" +
                "respCode=" + respCode +
                ", respSize=" + respSize +
                ", respTime=" + respTime +
                '}';
    }
}
