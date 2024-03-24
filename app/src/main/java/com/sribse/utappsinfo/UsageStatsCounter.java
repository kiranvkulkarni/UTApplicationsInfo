package com.sribse.utappsinfo;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsageStatsCounter {

    public static Map<String, Integer> getUsageCounts(Context context, List<String> packageNames) {
        Map<String, Integer> usageCounts = new HashMap<>();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            return usageCounts;
        }

        long startTime = System.currentTimeMillis() - 86400000; // 24 hours ago
        long endTime = System.currentTimeMillis();

        List<UsageStats> statsList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            statsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime);
        } else {
            statsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
        }

        if (statsList != null) {
            for (UsageStats usageStats : statsList) {
                String packageName = usageStats.getPackageName();
                if (packageNames.contains(packageName)) {
                    int usageCount = usageCounts.getOrDefault(packageName, 0);
                    usageCounts.put(packageName, usageCount + 1);
                }
            }
        }
        return usageCounts;
    }
}

