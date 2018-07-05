/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.notes.notes;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.testing.notes.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.testing.notes.utils.JsonReader.readJsonFromUrl;
import static org.hamcrest.Matchers.allOf;

/**
 * Tests for the notes screen, the main screen which contains a grid of all notes.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotesScreenTest {


    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<NotesActivity> mNotesActivityTestRule =
            new ActivityTestRule<>(NotesActivity.class);

    /**
     * Verify if the flow action button '+' is displayed to the user
     * Check also the title from the notes screen
     * Click add note and verify if the add note ui is opened
     * @throws Exception
     */
    @Test
    public void clickAddNoteButton_opensAddNoteUi() throws Exception {
        //Check FAB is displayed
        onView(withId(R.id.fab_add_notes)).check(matches(isDisplayed()));

        //Check Title
        String title = InstrumentationRegistry.getTargetContext()
                .getString(R.string.app_name);
        onView(allOf(withId(R.id.toolbar), hasDescendant(withText(title))))
                .check(matches(isDisplayed()));

        // Click on the add note button
        onView(withId(R.id.fab_add_notes)).perform(click());


        // Check if the add note screen is displayed
        onView(withId(R.id.add_note_title)).check(matches(isDisplayed()));
    }

    /**
     * The test will click the add note button, add a note with a populated title and description,
     * save the note, scroll notes list to added note, by finding its description
     * Verify if note is saved and displayed on screen
     * @throws Exception
     */
    @Test
    public void addNoteToNotesList() throws Exception {
        for (int i=0; i<10; i++) {
            JSONObject json = readJsonFromUrl("https://randomuser.me/api");
            JSONArray arr = json.getJSONArray("results");

            String newNoteTitle = arr.getJSONObject(0).getJSONObject("name").getString("first");
            String newNoteDescription = arr.getJSONObject(0).getJSONObject("location").getString("street");



            // Click on the add note button
            onView(withId(R.id.fab_add_notes)).perform(click());

            // Add note title and description
            // Type new note title
            onView(withId(R.id.add_note_title)).perform(replaceText(newNoteTitle), closeSoftKeyboard());
            onView(withId(R.id.add_note_description)).perform(replaceText(newNoteDescription),
                    closeSoftKeyboard()); // Type new note description and close the keyboard

            // Save the note
            onView(withId(R.id.fab_add_notes)).perform(click());

            // Scroll notes list to added note, by finding its description
            onView(withId(R.id.notes_list)).perform(
                    scrollTo(hasDescendant(withText(newNoteDescription))));

            // Verify note is displayed on screen
            onView(withText(newNoteDescription)).check(matches(isDisplayed()));
        }
    }

}