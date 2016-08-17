package davidcrotty.androiduitesting;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;

import davidcrotty.androiduitesting.testhelper.DisableAnimationsRule;
import davidcrotty.androiduitesting.view.ComplexView;

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

    @ClassRule
    public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getActivity();
    }

    public void test_when_pressing_hello_button() {
        onView(withId(R.id.action_button))
        .perform(click());

        onView(withId(R.id.button_result_text))
        .check(matches(withText("Hello")));
    }

    public void test_when_toggling_switch() {
        onView(withId(R.id.toggle_switch))
        .perform(click())
        .check(matches(isChecked()));

        onView(withId(R.id.visibility_button))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    /**
     * By the normal flow of espresso a view can be retreived and queried, provided it does not touch
     * the UI thread.
     */
    public void test_when_interacting_with_complex_view_non_ui_item() {
        onView(withId(R.id.complex_view));
        final ComplexView complexView = (ComplexView) getActivity().findViewById(R.id.complex_view);
        int state = complexView.getSomeState();

        Assert.assertEquals(1, state);
    }

    /**
     * Touches the UI, requires a sleep as the UI thread is touched. This is not good practice and
     * a UIControllerShould be used.
     */
    public void test_when_interacting_with_complex_view_with_ui_item() {
        onView(withId(R.id.complex_view));
        final ComplexView complexView = (ComplexView) getActivity().findViewById(R.id.complex_view);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                complexView.hideSpinner();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.radio_button))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

//    public void test_when_hiding_spinner() {
//        onView(withId(R.id.hide_spinner_button))
//        .perform(click());
//
//        onView(withId(R.id.progress_bar))
//        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
//    }
}
