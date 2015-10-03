package co.hackingedu.ro;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Joseph on 9/25/15.
 */
public class App extends Application {

    private static Context context;

    private final String PARSE_APP_ID = "6iCemurReQldHcZGy9SgMjMKpIM1SVb91lzD8sKi";

    private final String PARSE_CLIENT_KEY = "j9yKLdSIcXEO8w53Aiz8VwKanWqgmlWXwsb92Cfo";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }
}
