package davidcrotty.androiduitesting.presenter;

import android.util.Log;

import javax.inject.Inject;

import davidcrotty.androiduitesting.service.StarWarsService;

/**
 * Created by DavidHome on 24/10/2016.
 */
public class ToothPickPresenter {
    private static final String TAG = "ToothPickPresenter";

    private StarWarsService _starWarsService;

    @Inject
    public ToothPickPresenter(StarWarsService starWarsService) {
        _starWarsService = starWarsService;
    }

    public void doSomething() {
        Log.d(TAG, "doSomething: ");
    }
}
