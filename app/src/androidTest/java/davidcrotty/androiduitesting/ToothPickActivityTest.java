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
 * Created by DavidHome on 07/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ToothPickActivityTest {

    @Rule
    public ActivityTestRule<ToothPickActivity> activityRule =
            new ActivityTestRule<>(ToothPickActivity.class);

    @Before
    public void before_test() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(20, TimeUnit.SECONDS);

        ProgressIdlingResource progressIdlingResource = new ProgressIdlingResource(activityRule.getActivity());

        Espresso.registerIdlingResources(progressIdlingResource);
    }

    @Test
    public void test_when_fetching_content() {
        onView(withId(R.id.fetch_button)).perform(click());
        onView(withId(R.id.name_text))
        .check(ViewAssertions.matches(withText("Luke Skywalker")));
    }
}
