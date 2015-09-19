package co.hackingedu.ro.backend;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Joseph on 9/18/15.
 */
public class CacheManager {
    /**
     * Endpoints of app to use in get function
     * For usage in specifying JSON Files to update
     * from each respective endpoint
     */
    public final String FAQS_ENDPOINT = "/faqs",
            GENERAL_ENDPOINT = "/general",
            EVENTS_ENDPOINT = "/events",
            NOTIFS_ENDPOINT = "/notifications",
            MAPS_ENDPOINT = "/maps";

    /**
     * Backend Manager to handle API Calls and update Cached information
     */
    private BackendManager backendManager;

    /**
     * Global Shared Preferences to handle preference manipulation within class
     */
    private SharedPreferences sharedPreferences;

    /**
     * Constructor Method
     */
    public CacheManager(){
        backendManager = new BackendManager();
    }

    /**
     * Public void to poll server for updates on all JSON Files
     */
    public void updatedAllJSONFiles(Context _context) throws IOException, JSONException {
        updateJsonFile(FAQS_ENDPOINT, _context);
        updateJsonFile(GENERAL_ENDPOINT, _context);
        updateJsonFile(EVENTS_ENDPOINT, _context);
        updateJsonFile(NOTIFS_ENDPOINT, _context);
        updateJsonFile(MAPS_ENDPOINT, _context);
    }

    /**
     * Public void to poll server for updates on specified JSON File
     * @param JsonFile name of file to update
     */
    public void updateJsonFile(String JsonFile, Context _context) throws IOException, JSONException {
        // utilize the backend manager to call the API
        String JsonToString = ((JSONArray) backendManager.get(JsonFile)).toString();

        // locally save the JSON String as a preference accessible by the context
        sharedPreferences = getSharedPreferences(JsonFile, _context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(JsonFile, JsonToString);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Static getter for shared Preferences
     * @param prefName
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(String prefName, Context context) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}
