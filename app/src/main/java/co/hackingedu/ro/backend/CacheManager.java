package co.hackingedu.ro.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Joseph on 9/18/15.
 * TODO: Checking for whether file exists or not
 * TODO: Online testing for putting and retrieving
 */
public class CacheManager {
    /**
     * Logging Tag
     */
    private final String TAG = "CacheManager";

    /**
     * Endpoints of app to use in get function
     * For usage in specifying JSON Files to update
     * from each respective endpoint
     */
    public final String FAQS_FILE = "/faqs",
            GENERAL_FILE = "/general",
            EVENTS_FILE = "/events",
            NOTIFS_FILE = "/notifications",
            MAPS_FILE = "/maps";

    /**
     * Backend Manager to handle API Calls and update Cached information
     */
    private BackendManager backendManager;

    /**
     * Global Shared Preferences to handle local storage manipulation within class
     */
    private SharedPreferences sharedPreferences;

    /**
     * Default Constructor for complete manual manipulation of local storage
     */
    public CacheManager(){
        backendManager = new BackendManager();
    }

    /**
     * Constructor Method to update everything
     * Updates all local JSON files
     */
    public CacheManager(Context _context) throws IOException, JSONException {
        backendManager = new BackendManager();
        updateAllJSONFiles(_context);
    }

    /**
     * Constructor Method with specified JSON File to update
     * @param _JsonFile
     * @param _context
     * @throws IOException
     * @throws JSONException
     */
    public CacheManager(String _JsonFile, Context _context) throws IOException, JSONException {
        backendManager = new BackendManager();
        updateJsonFile(_JsonFile, _context);
    }

    /**
     * Public void to poll server for updates on all JSON Files
     */
    public void updateAllJSONFiles(Context _context) throws IOException, JSONException {
        updateJsonFile(FAQS_FILE, _context);
        updateJsonFile(GENERAL_FILE, _context);
        updateJsonFile(EVENTS_FILE, _context);
        updateJsonFile(NOTIFS_FILE, _context);
        updateJsonFile(MAPS_FILE, _context);
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
     * @param prefName JSON File Name
     * @param context TODO: Something I need to look more into on why context is necessary
     * @return stored information
     */
    public SharedPreferences getSharedPreferences(String prefName, Context context) {
        Log.i(TAG, "local file retrieved: " +
                context.getSharedPreferences(prefName, Context.MODE_PRIVATE));
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}
