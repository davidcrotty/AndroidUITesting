package davidcrotty.androiduitesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import davidcrotty.androiduitesting.presenter.ToothPickPresenter;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * Created by DavidHome on 24/10/2016.
 */

public class ToothPickActivity extends AppCompatActivity {

    @Inject
    ToothPickPresenter _presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toothpick);

        Scope scope = Toothpick.openScope(getApplication());
        scope.installModules(new Module(){{
            bind(ToothPickPresenter.class).to(ToothPickPresenter.class);
        }});
        Toothpick.inject(this, scope);
        _presenter.doSomething();
    }
}
