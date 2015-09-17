package co.hackingedu.ro.backend;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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
     * @param _endpoint final endpoint to call api
     * @return Object to parse as JSONArray or JSONObject - needs more robustness on this end
     * @throws IOException I/O Stream error getting input from API
     * @throws JSONException JSON conversion error on Array vs Object
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

        // TODO: error handling
        Object responseObject;
        JSONArray responseJsonArray;
        JSONObject responseJsonObject;

        // return JSON content
        responseObject = new JSONArray(responseStrBuilder.toString());

        //responseObject = json;
        if (responseObject instanceof JSONArray) {
            // It's an array
            Log.i(TAG, "array found!");
            return responseJsonArray = (JSONArray)responseObject;
        }
        else if (responseObject instanceof JSONObject) {
            // It's an object
            Log.i(TAG, "object found!");
            return responseJsonObject = (JSONObject)responseObject;
        }
        else {
            // It's something else, like a string or number
            Log.i(TAG, "value found!");
            return responseObject;
        }
    }

    /**
     * TODO: CACHING
     * Void method to store JSON to phone storage as cache
     */
    public File getTempFile(Context context, String url) throws IOException {
        File file;
        String fileName = Uri.parse(url).getLastPathSegment();
        file = File.createTempFile(fileName, null, context.getCacheDir());
        return file;
    }

    /**
     * TODO: modular testing
     * @param objToTest
     * @return
     */
    private boolean isArray(Object objToTest)
    {
        return (objToTest instanceof JSONArray);
    }

    private boolean isObject(Object objToTest)
    {
        return (objToTest instanceof JSONObject);
    }
}