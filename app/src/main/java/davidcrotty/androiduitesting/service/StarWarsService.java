package davidcrotty.androiduitesting.service;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.inject.Inject;

import davidcrotty.androiduitesting.model.Person;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by DavidHome on 26/10/2016.
 */

public class StarWarsService {

    private static final String TAG = "StarWarsService";
    private final Gson _gson;

    @Inject
    public StarWarsService(Gson gson) {
        Log.d(TAG, "StarWarsService: created");
        _gson = gson;
    }

    public Observable<Person> getperson() {

        return Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                final URL url;
                try {
                    url = new URL("http://swapi.co/api/people/1/");
                } catch (MalformedURLException e) {
                    return;
                }

                HttpURLConnection urlConnection = null;
                InputStream stream = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);

                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        stream = new BufferedInputStream(urlConnection.getInputStream());

                        final int bufferSize = 1024;
                        final char[] buffer = new char[bufferSize];
                        final StringBuilder out = new StringBuilder();
                        Reader in = new InputStreamReader(stream, "UTF-8");
                        for (; ; ) {
                            int rsz = in.read(buffer, 0, buffer.length);
                            if (rsz < 0)
                                break;
                            out.append(buffer, 0, rsz);
                        }

                        subscriber.onNext(_gson.fromJson(out.toString(), Person.class));
                    }
                } catch (IOException e) { /* ignore */
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) { /* ignore */
                        }
                    }
                }
            }
        });
    }
}
