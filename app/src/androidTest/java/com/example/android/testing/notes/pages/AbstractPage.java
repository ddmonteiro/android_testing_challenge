package com.example.android.testing.notes.pages;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

public abstract class AbstractPage {

    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int LOADING_TIMEOUT = 20000;

    private static final String PROGRESS_BAR_RESOURCE_ID = "id/toolbar_loading_bar";

    private UiObject mProgressBar;

    protected abstract void initUIObjects();

    public abstract boolean isPageVisible() throws UiObjectNotFoundException;
}

