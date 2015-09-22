package co.hackingedu.ro.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
     * Editor to Shared Preferences
     */
    private SharedPreferences.Editor editor;

    /**
     * String to designate access to our local storage
     */
    private static final String PREF_NAME = "HackingEDU_Data";

    /**
     *
     * @param _sharedPreferences
     */
    public CacheManager(SharedPreferences _sharedPreferences){
        backendManager = new BackendManager();
        sharedPreferences = _sharedPreferences;
        editor = sharedPreferences.edit();
    }

    /**
     * Constructor Method to update everything
     * Updates all local JSON files
     */
    public CacheManager(Context _context) throws IOException, JSONException {
        Log.i(TAG, "instantiating BackendManager");
        backendManager = new BackendManager();
        Log.i(TAG, "instantiating sharedPreferences");
        sharedPreferences = getSharedPreferences(_context);
        editor = sharedPreferences.edit();
        updateAllJSONFiles();
    }

    /**
     * Constructor Method with specified JSON File to update
     * @param _JsonFile specified JSON File to update
     * @param _context Android Activity context
     * @throws IOException thrown when issues with backendManager are faced
     * @throws JSONException thrown when JSON conversion issues are faced
     */
    public CacheManager(String _JsonFile, Context _context) throws IOException, JSONException {
        Log.i(TAG, "instantiating BackendManager");
        backendManager = new BackendManager();
        Log.i(TAG, "instantiating sharedPreferences");
        sharedPreferences = getSharedPreferences(_context);
        editor = sharedPreferences.edit();
        Log.i(TAG, "updating file");
        updateJsonFile(_JsonFile);
    }

    /**
     * Public void to poll server for updates on all JSON Files
     */
    public void updateAllJSONFiles() throws IOException, JSONException {
        updateJsonFile(FAQS_FILE);
        updateJsonFile(GENERAL_FILE);
        updateJsonFile(EVENTS_FILE);
        updateJsonFile(NOTIFS_FILE);
        updateJsonFile(MAPS_FILE);
    }

    /**
     * Public void to poll server for updates on specified JSON File
     * @param JsonFile name of file to update
     */
    public void updateJsonFile(String JsonFile) throws IOException, JSONException {
        // utilize the backend manager to call the API
        Log.i(TAG, "File to update: " + JsonFile);
        String JsonToString = (backendManager.get(JsonFile)).toString();
        Log.i(TAG, "JsonToString: " + JsonToString);
        // locally save the JSON String as a preference accessible by the context
        editor.putString(JsonFile, JsonToString);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Static getter for shared Preferences
     * @param context TODO: Something I need to look more into on why context is necessary
     * @return stored information
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        //return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Public status checker method for the existence of a JsonFile
     * @param JsonFile File to check
     * @return whether getString succeeds
     */
    public boolean checkFile(String JsonFile){
        return (sharedPreferences.getString(JsonFile, null) != null);
    }

    /**
     * Public Getter method to retrieve local storage as String
     * @param JsonFile specified JSON file to retrieve
     * @param _context provided context from Android Activity
     * @return locally stored file contents as String
     */
    public String getJsonString(String JsonFile, Context _context){
        String str = getSharedPreferences(_context).getString(JsonFile, null);
        Log.i(TAG, str);
        return str;
    }

    /**
     * Public getter method to retrieve local storage as JSONArray
     * @param JsonFile specified JSON file to retrieve
     * @param _context provided context from Android Activity
     * @return locally stored file contents as JSONArray
     * @throws JSONException whenever there is an issue with String-JSON conversion
     */
    public JSONArray getJsonArray(String JsonFile, Context _context) throws JSONException {
        if(getJsonString(JsonFile, _context) == null){
            // handle this issue!
            return null;
        }
        return new JSONArray(getJsonString(JsonFile, _context));
    }

    /**
     * Public Deleter method to delete a single JSON File
     * @param JsonFile specified JSON File to delete
     */
    public void removeJsonFile(String JsonFile){
        // delete key, and thus the data as well
        editor.remove(JsonFile);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Public deleter method to delete all preference data
     */
    public void clearAll(){
        // delete key, and thus the data as well
        editor.clear();

        // Commit the edits!
        editor.commit();
    }
}
