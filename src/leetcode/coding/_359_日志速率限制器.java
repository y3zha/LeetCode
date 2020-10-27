package leetcode.coding;

import java.util.HashMap;

public class _359_日志速率限制器 {

    class Logger {

        // 日志内容 -> 时间戳
        HashMap<String, Integer> logs;

        public Logger() {
            logs = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (logs.containsKey(message)) {
                Integer preTimeStamp = logs.get(message);
                if (timestamp - preTimeStamp >= 10) {
                    logs.put(message, timestamp);
                    return true;
                } else {
                    return false;
                }
            } else {
                logs.put(message, timestamp);
                return true;
            }
        }
    }
}