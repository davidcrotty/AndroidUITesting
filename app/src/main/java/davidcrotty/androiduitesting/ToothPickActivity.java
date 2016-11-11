package davidcrotty.androiduitesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import davidcrotty.androiduitesting.presenter.ToothPickPresenter;
import davidcrotty.androiduitesting.service.StarWarsService;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * Created by DavidHome on 24/10/2016.
 */
public class ToothPickActivity extends AppCompatActivity {

    public interface ProgressListener {
        void onProgressShown();
        void onProgressDismissed();
    }

    private ProgressListener _listener;

    @Inject
    ToothPickPresenter _presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toothpick);

        Scope scope = Toothpick.openScope(getApplication());
        scope.installModules(new Module(){{
            bind(ToothPickActivity.class).toInstance(ToothPickActivity.this);
            bind(Gson.class).toInstance(new Gson());
            bind(StarWarsService.class).to(StarWarsService.class);
            bind(ToothPickPresenter.class).to(ToothPickPresenter.class);
        }});
        Toothpick.inject(this, scope);

        Button fetchButton = (Button) findViewById(R.id.fetch_button);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _listener.onProgressShown();
                _presenter.fetchPerson();
            }
        });
    }

    public void showPersonWith(String name, String height) {
        TextView nameText = (TextView) findViewById(R.id.name_text);
        nameText.setText(name);

        TextView heightText = (TextView) findViewById(R.id.height_text);
        heightText.setText(height);
        if(_listener == null) return;
        _listener.onProgressDismissed();
    }

    public void setProgressListenerWith(ProgressListener listener) {
        _listener = listener;
    }
}
