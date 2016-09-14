package davidcrotty.androiduitesting;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
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
//FIXME ActivityInstrumentationTestCase2 deprecated in N, see https://developer.android.com/reference/android/test/ActivityInstrumentationTestCase2.html
public class IntentActivityTest extends ActivityInstrumentationTestCase2<IntentActivity> {
    public IntentActivityTest() {
        super(IntentActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Intent intent = new Intent();
        intent.putExtra("TEXT_KEY", "hello");
        setActivityIntent(intent);
        getActivity();
    }

    @Test
    public void test_when_not_passing_intent() {
        onView(withId(R.id.text_view))
        .check(matches(withText("")));
    }

    @Test
    public void test_when_passing_intent() {
        onView(withId(R.id.text_view))
        .check(matches(withText("hello")));
    }
}
