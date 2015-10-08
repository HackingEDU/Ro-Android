package co.hackingedu.ro;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Joseph on 9/25/15.
 */
public class App extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "cvTR90KaXdMgiN2rGFLO54iMQ";
    private static final String TWITTER_SECRET = "H4Yu2JDJU2963Cos4ujelsKfRjo1qlb3FwJNgonZW4oUhfqsWC";


    private static Context context;

    private final String PARSE_APP_ID = "6iCemurReQldHcZGy9SgMjMKpIM1SVb91lzD8sKi";

    private final String PARSE_CLIENT_KEY = "j9yKLdSIcXEO8w53Aiz8VwKanWqgmlWXwsb92Cfo";

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }
}
