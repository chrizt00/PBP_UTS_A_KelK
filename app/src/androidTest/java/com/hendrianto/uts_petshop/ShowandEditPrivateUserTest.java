package com.hendrianto.uts_petshop;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShowandEditPrivateUserTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void showandEditPrivateUserTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.emailLogin),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.emailLogin), withText("hendrianto8901234@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.passwordLogin),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("abcdefg"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.passwordLogin), withText("abcdefg"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.memenu), withContentDescription("Me"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.update), withText("update"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.nameInput), withText("haha"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("hahanama"));

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.nameInput), withText("hahanama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.nameInput), withText("hahanama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(pressImeActionButton());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.nameInput), withText("hahanama"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(pressImeActionButton());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.telpInput), withText("4646"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.telpLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.telpInput), withText("4646"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.telpLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("46464646"));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.telpInput), withText("46464646"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.telpLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.telpInput), withText("46464646"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.telpLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(pressImeActionButton());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.update), withText("Simpan Perubahan"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.logout), withText("logout"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                4)));
        materialButton4.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
