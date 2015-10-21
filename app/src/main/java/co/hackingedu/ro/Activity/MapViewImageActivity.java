package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import co.hackingedu.ro.App;
import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.CacheManager;

public class MapViewImageActivity extends Activity {

    /**
     * Debugging Tag
     */
    private static String TAG = "MapViewImageActivity";

    /**
     *
     */
    private static final String INTENT_EXTRA_MAP_KEY = "map_map";

    /**
     *
     */
    private static final String INTENT_EXTRA_IMAGE_KEY = "map_img";

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view_image);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // retrieve previous intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // read extras
        if(bundle != null)
        {
            // get View
            View view = findViewById(R.id.map_view_image_activity);

            // get map name
            String mapMap = (String) bundle.get(INTENT_EXTRA_MAP_KEY);
            Log.i(TAG, "mapMap: " + mapMap);

            // set some textView to this text
            // todo: utilize the text with a TextView or something

            // get map image url
            String mapURL = (String) bundle.get(INTENT_EXTRA_IMAGE_KEY);
            Log.i(TAG, "mapURL: " + mapURL);

            // convert url to drawable bitmap image
            //BitmapDrawable d = new BitmapDrawable(App.getAppContext().getResources(),
            //        getBitmapFromURL(mapURL));

            // set background to drawable bitmap
            //view.setBackground(d);

        }
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /**
     * Helper method to get Bitmap from URL
     * @param src
     * @return
     */
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e(TAG, "src: " + src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e(TAG, "Bitmap returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    /**
     * Helper method to get Drawable from URL
     * @param url
     * @return
     * @throws IOException
     */
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(App.getAppContext().getResources(), x);
    }
}