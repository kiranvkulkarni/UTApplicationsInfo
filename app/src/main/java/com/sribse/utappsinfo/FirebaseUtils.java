package com.sribse.utappsinfo;

import android.content.Context;

import java.util.List;
import java.util.Map;

public class FirebaseUtils {

    public static List<String> getPackageNamesFromFirebase(Context context) {
        // Retrieve package names from Firebase database
        // You can use Firebase SDK to fetch package names asynchronously
        // For simplicity, let's create a sample list here
        return SamplePackageNames.getSamplePackageNames();
    }

    public static void uploadUsageStatsToFirebase(Map<String, Integer> usageCounts) {
        // Upload usage counts to Firebase
        // This could be similar to the method provided earlier
    }
}

