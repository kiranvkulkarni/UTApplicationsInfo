package com.sribse.utappsinfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class ScheduleUtils {

    public static void scheduleDailyTask(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, UsageStatsService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, DAILY_ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cancel previous pending intent
        alarmManager.cancel(pendingIntent);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13); // 1 PM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Check if alarm manager already triggered for today
        if (System.currentTimeMillis() > calendar.getTimeInMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Schedule for next day
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
