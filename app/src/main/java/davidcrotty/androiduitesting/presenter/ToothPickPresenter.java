package davidcrotty.androiduitesting.presenter;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by DavidHome on 24/10/2016.
 */
public class ToothPickPresenter {
    private static final String TAG = "ToothPickPresenter";

    @Inject
    public ToothPickPresenter() {
    }

    public void doSomething() {
        Log.d(TAG, "doSomething: ");
    }
}
