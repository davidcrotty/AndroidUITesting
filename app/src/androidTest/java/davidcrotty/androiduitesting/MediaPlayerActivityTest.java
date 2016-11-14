package davidcrotty.androiduitesting;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import davidcrotty.androiduitesting.helper.ProgressIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by DavidHome on 14/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MediaPlayerActivityTest {

    @Rule
    public ActivityTestRule<MediaPlayerActivity> activityRule =
            new ActivityTestRule<>(MediaPlayerActivity.class);

    @Before
    public void before_test() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(20, TimeUnit.SECONDS);

        ProgressIdlingResource progressIdlingResource = new ProgressIdlingResource(activityRule.getActivity());

        Espresso.registerIdlingResources(progressIdlingResource);
    }

    @Test
    public void test_media_player_loaded() {
        //Without a user action test will fail
        onView(withId(R.id.load_button)).perform(click());

        onView(withId(R.id.video_loaded_text))
        .check(ViewAssertions.matches(withText("Loaded")));
    }
}
