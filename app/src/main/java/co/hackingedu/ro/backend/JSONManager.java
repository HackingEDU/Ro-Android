package co.hackingedu.ro.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joseph on 9/17/15.
 */
public class JsonManager {
    /**
     * Used as base index
     */
    private final int ZERO = 0;

    public JsonManager() {

    }

    /**
     * Getter method to get a query from a JSONArray
     * @param query key
     * @param index index to specify object
     * @param arrayToParse input JSONArray
     * @return String of content
     * @throws JSONException error in parsing JSONArray
     */
    public String get(String query, int index, JSONArray arrayToParse) throws JSONException {
        String response = "";
        // get Object
        JSONObject parsedObj = (JSONObject) arrayToParse.get(index);
        response = (String) parsedObj.get(query);
        return response;
    }

    /**
     * Default getter method to get the first object from an array
     * @param query
     * @param arrayToParse
     * @return
     * @throws JSONException
     */
    public String get(String query, JSONArray arrayToParse) throws JSONException {
        String response = "";
        // get Object
        JSONObject parsedObj = (JSONObject) arrayToParse.get(ZERO);
        response = (String) parsedObj.get(query);
        return response;
    }

    public int length(JSONArray arrayToLength){
        int len = 0;
        len = arrayToLength.length();
        return len;
    }
}
