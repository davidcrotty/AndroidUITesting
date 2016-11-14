package davidcrotty.androiduitesting.helper;

import android.support.test.espresso.IdlingResource;

import org.jetbrains.annotations.NotNull;

import davidcrotty.androiduitesting.ProgressListener;
import davidcrotty.androiduitesting.ToothPickActivity;

/**
 * Created by DavidHome on 07/11/2016.
 */

public class ProgressIdlingResource implements IdlingResource {

    private ProgressListener _targetActivity;
    private ResourceCallback _callback;
    private boolean progressDismissed = true;

    public ProgressIdlingResource(ProgressListener toothPickActivity) {
        _targetActivity = toothPickActivity;

        ProgressListener listener = new ProgressListener() {
            @Override
            public void setProgressListenerWith(@NotNull ProgressListener listener) {
                //no op
            }

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

        _targetActivity.setProgressListenerWith(listener);
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
