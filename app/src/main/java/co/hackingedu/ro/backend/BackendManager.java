package co.hackingedu.ro.backend;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Joseph on 9/16/15.
 */
public class BackendManager {

    /**
     * Logging Tag
     */
    private final String TAG = "BackendManager";

    /**
     * Endpoints of app
     */
    private final String FAQS_ENDPOINT = "/faqs",
            GENERAL_ENDPOINT = "/general",
            EVENTS_ENDPOINT = "/events",
            NOTIFS_ENDPOINT = "/notifications",
            MAPS_ENDPOINT = "/maps",
            NODE_ENDPOINT = "http://hackingedu.herokuapp.com";

    // endpoint
    private URL url;

    /**
     * Constructor
     */
    public BackendManager() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * Get endpoint to get JSON response from FAQs
     * @return true or false on success
     */
    public JSONObject connectFaqs() throws IOException, JSONException {
        // build URL String
        String urlString = ""
                + NODE_ENDPOINT
                + FAQS_ENDPOINT;

        // prepare URL for connection
        url = new URL(urlString);

        // open stream for reading input
        InputStream inputStream = url.openStream();
        InputStreamReader reader = new InputStreamReader(inputStream);

        // buffer stream and read to String
        BufferedReader streamReader = new BufferedReader(reader);
        StringBuilder responseStrBuilder = new StringBuilder();

        // convert String to JSON object
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            Log.i(TAG, "endpoint content: " + inputStr);
            responseStrBuilder.append(inputStr);
        }

        // return JSON content
        return new JSONObject(responseStrBuilder.toString());
    }


}