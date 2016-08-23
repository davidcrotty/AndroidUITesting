package davidcrotty.androiduitesting;

import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ProgressBar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getActivity();

        /* Options:
         * - directly mod class to detect when its a test case
         * see:
         */
        
        /*
         * Running a test using an indeterminate spinner causes the test to block as it is run on the
         * same thread as the activity see: http://stackoverflow.com/questions/33289152/progressbars-and-espresso
         *
         * This is an issue in the Android Testing Framework, and the only intuitive workaround is to
         * hide/replace the spinner when testing. This cannot be done here as to reference a view you need
         * to call getActivity(), however once this is called you are in the instrumentation test loop and
         * as per above cannot go any further due to hanging. Leading to the least 'hacky' work around being
         * a gradle config option when running UI tests, but guess what? You can't tell!
         *
         * The only 'reliable way' is trying to load the test class in the app to see if we are running a ui
         * test or not. However Insturmentation does not appear to instantiate your base Application class, so
         * this has to be done in the activity under test. Hopefully Google can fix this as it seems like quite
         * a major defect in the TestingFramework/Build system.
         *
         * see: http://stackoverflow.com/questions/33289152/progressbars-and-espresso
         */
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
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    /**
     * By the normal flow of espresso a view can be retreived and queried, provided it does not touch
     * the UI thread.
     */
    @Test
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
    @Test
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
