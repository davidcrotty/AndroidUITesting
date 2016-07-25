package davidcrotty.androiduitesting;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by DavidHome on 24/07/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity _activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        _activity = getActivity();
    }

    @Test
    public void test_when_pressing_hello_button() {
        onView(withId(R.id.action_button))
        .perform(click());

        onView(withId(R.id.button_result_text))
        .check(matches(withText("Hello")));
    }

    @Test
    public void test_when_toggling_switch() {
        onView(withId(R.id.toggle_switch))
        .perform(click())
        .check(matches(isChecked()));

        onView(withId(R.id.visibility_button))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
}
