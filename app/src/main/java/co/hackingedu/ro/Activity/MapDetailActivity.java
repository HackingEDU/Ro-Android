package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import co.hackingedu.ro.R;

public class MapDetailActivity extends Activity {
    /**
     * Debugging Tag
     */
    private static String TAG = "MapDetailActivity";

    /**
     *
     */
    private static final String INTENT_EXTRA_MAP_KEY = "map";

    /**
     *
     */
    private static final String INTENT_EXTRA_IMAGE_KEY = "img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view_image);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //trying to get the getExtra data and set it to textView
        TextView speakerName = (TextView)findViewById(R.id.speaker_name);

        View view = (View) findViewById(R.id.map_img);

        // retrieve previous intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // read extras
        if(bundle != null)
        {

            // set event speaker name
            String mapName = (String) bundle.get(INTENT_EXTRA_MAP_KEY);
            Log.i(TAG, mapName);
            speakerName.setText(mapName);

            // set event speaker image
            String mapURL = (String) bundle.get(INTENT_EXTRA_IMAGE_KEY);
            Log.i(TAG, mapURL);
            try {
                view.setBackground(drawableFromUrl(mapURL));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                this.finish();
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
        return new BitmapDrawable(x);
    }
}