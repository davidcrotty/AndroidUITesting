package davidcrotty.androiduitesting.module;

import javax.inject.Provider;

import davidcrotty.androiduitesting.presenter.ToothPickPresenter;

/**
 * Created by DavidHome on 24/10/2016.
 */

public class ToothPickPresenterProvider implements Provider<ToothPickPresenter> {
    @Override
    public ToothPickPresenter get() {
        return new ToothPickPresenter();
    }
}
