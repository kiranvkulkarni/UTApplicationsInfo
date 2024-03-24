package com.sribse.utappsinfo;

import android.content.Context;
import android.os.Build;

import java.util.List;
import java.util.Map;

public class UsageStatsHelper {

    public static void getAndUploadUsageStats(Context context) {
        // Get usage stats for particular packages
        List<String> packageNames = FirebaseUtils.getPackageNamesFromFirebase(context);
        if (packageNames != null && !packageNames.isEmpty()) {
            Map<String, Integer> usageCounts = UsageStatsCounter.getUsageCounts(context, packageNames);
            if (usageCounts != null && !usageCounts.isEmpty()) {
                // Store usage stats in SQLite database
                SQLiteHelper.storeUsageStatsInDatabase(context, usageCounts);
                // Upload usage stats to Firebase
                FirebaseUtils.uploadUsageStatsToFirebase(usageCounts);
            }
        }
    }
}

