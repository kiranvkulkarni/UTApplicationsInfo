package com.sribse.utappsinfo;

import android.app.IntentService;
import android.content.Intent;

public class UsageStatsService extends IntentService {

    public UsageStatsService() {
        super("UsageStatsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            // Get usage stats and upload to Firebase
            UsageStatsHelper.getAndUploadUsageStats(getApplicationContext());
        }
    }
}

