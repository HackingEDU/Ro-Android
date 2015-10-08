package co.hackingedu.ro;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Joseph on 9/25/15.
 */
public class App extends Application {

    // TODO: obfuscating these keys so decompilers can't find them
    /**
     * Twitter App Key
     * Uses Joseph Zhong's Fabric app as of 6 Oct. 2015
     */
    private static final String TWITTER_KEY = "cvTR90KaXdMgiN2rGFLO54iMQ";

    /**
     * Twitter App Key
     */
    private static final String TWITTER_SECRET = "H4Yu2JDJU2963Cos4ujelsKfRjo1qlb3FwJNgonZW4oUhfqsWC";

    /**
     * Parse App ID
     * USES Max's app as of about a week ago from 6 Oct. 2015
     */
    private final String PARSE_APP_ID = "6iCemurReQldHcZGy9SgMjMKpIM1SVb91lzD8sKi";

    /**
     * Parse Client Key
     */
    private final String PARSE_CLIENT_KEY = "j9yKLdSIcXEO8w53Aiz8VwKanWqgmlWXwsb92Cfo";

    /**
     * Static context to use later in MapViewImageActivity and other static environments
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        // configures twitter app
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);

        // leverages Fabric to authenticate TwitterKit, Core and Composer
        Fabric.with(this, new Twitter(authConfig), new TwitterCore(authConfig), new TweetComposer());

        // Parse app!
        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        context = getApplicationContext();
    }

    /**
     * Getter method for getting static context
     * @return application context in static environment
     */
    public static Context getAppContext() {
        return App.context;
    }
}
