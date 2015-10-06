package co.hackingedu.ro.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import co.hackingedu.ro.R;

public class ScheduleDetailActivity extends Activity {
    /**
     * Debugging Tag
     */
    private static String TAG = "ScheduleDetailActivity";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_SPEAKER_KEY = "event_name";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_IMAGE_KEY = "event_image";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_ABOUT_KEY = "event_about";

    private static String JSON_NAME_KEY = "name";
    private static String JSON_IMAGE_KEY = "image";
    private static String JSON_ABOUT_KEY = "about";

    /**
     * onCreate handler
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_detail);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // retrieve previous intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // read extras
        if(bundle != null)
        {
            // get views
            TextView speakerName = (TextView)findViewById(R.id.speaker_name);
            ImageView imageView = (ImageView) findViewById(R.id.speaker_image);
            TextView eventDescript = (TextView) findViewById(R.id.description);

            // set event speaker name
            String eventSpeaker = (String) bundle.get(INTENT_EXTRA_SPEAKER_KEY);
            Log.i(TAG, "eventSpeaker: " + eventSpeaker);
            speakerName.setText(eventSpeaker);

            // set event speaker image
            String eventImage = (String) bundle.get(INTENT_EXTRA_IMAGE_KEY);
            Log.i(TAG, "eventImage: " + eventImage);
            imageView.setImageBitmap(getBitmapFromURL("http://thesource.com/wp-content/uploads/2015/04/Drake.jpg"));

            // set event speaker description
            String eventAbout = (String) bundle.get(INTENT_EXTRA_ABOUT_KEY);
            Log.i(TAG, eventAbout);
            eventDescript.setText(eventAbout);
        }
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_view_image, menu);
        return true;
    }

    /**
     *
     * @param menuItem
     * @return
     */
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
}
