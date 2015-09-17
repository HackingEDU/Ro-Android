package co.hackingedu.ro.backend;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
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
     * Endpoints of app to use in get function
     */
    public final String FAQS_ENDPOINT = "/faqs",
            GENERAL_ENDPOINT = "/general",
            EVENTS_ENDPOINT = "/events",
            NOTIFS_ENDPOINT = "/notifications",
            MAPS_ENDPOINT = "/maps";

    /**
     * Private Endpoint to app
     */
    private final String NODE_ENDPOINT = "http://hackingedu.herokuapp.com";

    // endpoint
    private URL url;

    /**
     * Constructor
     */
    public BackendManager() {
        // Allow all network accessing
        // this isn't necessarily good practice as it allows
        // bad, cpu-intensive or time-consuming networking
        StrictMode.ThreadPolicy policy
                = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * General get function to take advantage of the specified Endpoints
     * @param _endpoint
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public Object get(String _endpoint) throws IOException, JSONException {
        // build URL String
        String urlString = ""
                + NODE_ENDPOINT
                + _endpoint;

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

        JSONObject json;
        Object responseObject;
        JSONArray interventionJsonArray;
        JSONObject interventionObject;

        // return JSON content
        json = new JSONObject(responseStrBuilder.toString());

        responseObject = json;
        if (responseObject instanceof JSONArray) {
            // It's an array
            Log.i(TAG, "array found!");
            return interventionJsonArray = (JSONArray)responseObject;
        }
        else if (responseObject instanceof JSONObject) {
            // It's an object
            Log.i(TAG, "object found!");
            return interventionObject = (JSONObject)responseObject;
        }
        else {
            // It's something else, like a string or number
            Log.i(TAG, "value found!");
            return responseObject;
        }
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

    /**
     * Get endpoint to get JSON response from FAQs
     * @return true or false on success
     */
    public JSONObject connectGeneral() throws IOException, JSONException {
        // build URL String
        String urlString = ""
                + NODE_ENDPOINT
                + GENERAL_ENDPOINT;

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