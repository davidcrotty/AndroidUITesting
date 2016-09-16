package davidcrotty.androiduitesting;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by DavidHome on 24/08/2016.
 */
@RunWith(AndroidJUnit4.class)
public class IntentActivityTest {

    @Rule
    public ActivityTestRule<IntentActivity> activityRule =
            new ActivityTestRule<>(IntentActivity.class);


    @Test
    public void test_when_not_passing_intent() {
        onView(withId(R.id.text_view))
        .check(matches(withText("")));
    }

    @Test
    public void test_when_passing_intent() {
        Intent intent = new Intent();
        intent.putExtra("TEXT_KEY", "hello");
        activityRule.launchActivity(intent);

        onView(withId(R.id.text_view))
        .check(matches(withText("hello")));
    }
}
