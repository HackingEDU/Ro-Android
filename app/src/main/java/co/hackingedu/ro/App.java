package co.hackingedu.ro;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Joseph on 9/25/15.
 */
public class App extends Application {

    private final String PARSE_APP_ID = "nTbv002rk08wVJF0Vet2kcGVdkXC7tWKIcIKw5Yk";

    private final String PARSE_CLIENT_KEY = "oD8lv96vBBX2UZwaNyuiLQvtCHzAiAjDqimroIFZ";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
