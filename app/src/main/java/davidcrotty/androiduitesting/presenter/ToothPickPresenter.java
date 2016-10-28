package davidcrotty.androiduitesting.presenter;

import android.util.Log;

import javax.inject.Inject;

import davidcrotty.androiduitesting.ToothPickActivity;
import davidcrotty.androiduitesting.model.Person;
import davidcrotty.androiduitesting.service.StarWarsService;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by DavidHome on 24/10/2016.
 */
public class ToothPickPresenter {
    private static final String TAG = "ToothPickPresenter";

    private final StarWarsService _starWarsService;
    private final ToothPickActivity _toothPickActivity;

    @Inject
    public ToothPickPresenter(StarWarsService starWarsService,
                              ToothPickActivity toothPickActivity) {
        _starWarsService = starWarsService;
        _toothPickActivity = toothPickActivity;
    }

    public void fetchPerson() {
        _starWarsService.getperson().subscribeOn(Schedulers.io())
                                    .subscribe(new Action1<Person>() {
                                        @Override
                                        public void call(final Person person) {
                                            _toothPickActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    _toothPickActivity.showPersonWith(person.getName(), person.getHeight());
                                                }
                                            });
                                        }
                                    });
    }
}
