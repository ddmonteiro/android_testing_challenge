package com.example.android.testing.notes.shared;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class DeviceUtils {

    private static String sPackage;

    public static UiDevice getDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public static void openApp(String packageName) {
        sPackage = packageName;

        // Initialize UiDevice instance
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                10000);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(packageName);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)),
                10000);
    }

    public static String fullResourceId(String resourceId) {
        return InstrumentationRegistry.getTargetContext().getPackageName()+":"+resourceId;
    }

    public static void setPackage(String packageName){
        sPackage = packageName;
    }

    public static String getsPackage() {
        return sPackage;
    }

}
