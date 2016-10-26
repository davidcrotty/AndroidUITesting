package davidcrotty.androiduitesting.service;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by DavidHome on 26/10/2016.
 */

public class StarWarsService {

    private static final String TAG = "StarWarsService";

    @Inject
    public StarWarsService() {
        Log.d(TAG, "StarWarsService: created");
    }
}
