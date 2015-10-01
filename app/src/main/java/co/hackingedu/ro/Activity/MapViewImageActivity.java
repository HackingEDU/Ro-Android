package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.CacheManager;

public class MapViewImageActivity extends Activity {

    /**
     * cacheManager to handle calling from local storage
     */
    private CacheManager cacheManager;

    /**
     * Array of map
     */
    private JSONArray mapArray;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        // initialize cacheManager
//        cacheManager = new CacheManager(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
//        // try
//        try {
//            mapArray = cacheManager.getJsonArray(cacheManager.MAPS_FILE, getApplicationContext());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_map_view_image);

        View asdf = findViewById(R.id.map_view_image_activity);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_view_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
