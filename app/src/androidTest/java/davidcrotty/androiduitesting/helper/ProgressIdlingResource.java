package davidcrotty.androiduitesting.helper;

import android.support.test.espresso.IdlingResource;

import davidcrotty.androiduitesting.ToothPickActivity;

/**
 * Created by DavidHome on 07/11/2016.
 */

public class ProgressIdlingResource implements IdlingResource {

    private ToothPickActivity _toothPickActivity;
    private ResourceCallback _callback;
    private boolean progressDismissed = true;

    public ProgressIdlingResource(ToothPickActivity toothPickActivity) {
        _toothPickActivity = toothPickActivity;

        ToothPickActivity.ProgressListener listener = new ToothPickActivity.ProgressListener() {
            @Override
            public void onProgressShown() {
                progressDismissed = false;
            }

            @Override
            public void onProgressDismissed() {
                if(_callback == null) return;
                progressDismissed = true;
                _callback.onTransitionToIdle();
            }
        };

        _toothPickActivity.setProgressListenerWith(listener);
    }

    @Override
    public String getName() {
        return "ToothPickActivity loading resource";
    }

    @Override
    public boolean isIdleNow() {
        return progressDismissed;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        _callback = callback;
    }
}
