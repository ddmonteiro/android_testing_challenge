package com.example.android.testing.notes.notes;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.testing.notes.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatisticsScreenTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<NotesActivity> mActivityTestRule =
            new ActivityTestRule<>(NotesActivity.class);

    /**
     * Over statistics from navigation drawer
     * Verify if the page is opened
     */
    @Test
    public void clickOnStatisticsNavigationItem_ShowsStatisticsScreen() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .perform(open()); // Open Drawer

        // Start statistics screen.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.statistics_navigation_menu_item));

        // Check that statistics Activity was opened.
        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
                .getString(R.string.no_statistics_available);
        onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));

        //Check image placeholder
        onView(withId(R.id.no_statistics_placeholder))
                .check(matches(isDisplayed()));

    }

}
