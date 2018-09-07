package com.example.android.testing.notes.shared;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CustomMatcher {

    //Find the first element visible
    public static Matcher<View> first(final Matcher<View> expected ){

        return new TypeSafeMatcher<View>() {
            private boolean first = false;

            @Override
            protected boolean matchesSafely(View item) {

                if( expected.matches(item) && !first ){
                    return first = true;
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Matcher.first( " + expected.toString() + " )" );
            }
        };
    }

    //Remove html tags from local strings
    //public static String html2text(String html) {
    //    return Jsoup.parse(html).text();
    //}

}

