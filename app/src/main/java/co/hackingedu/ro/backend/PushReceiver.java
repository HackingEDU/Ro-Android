package co.hackingedu.ro.backend;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import co.hackingedu.ro.Activity.MainActivity;
import co.hackingedu.ro.R;

/**
 * Created by Joseph on 9/28/15.
 */
public class PushReceiver extends ParsePushBroadcastReceiver {
    /**
     * Debugging Tag
     **/
    private final String TAG = "PushReceiver";

    /**
     * This is the key to get parse's push notification data
     **/
    public static final String PARSE_DATA_KEY = "com.parse.Data";

    /**
     * Receiver of push notifications
     **/
    @Override
    protected Notification getNotification(Context context, Intent intent) {
        // deactivate standard notification
        return null;
    }

    /**
     * Receiver of opening push 
     **/
    @Override
    protected void onPushOpen(Context context, Intent intent) {
        // Implement
        JSONObject data = getDataFromIntent(intent);
        Log.i(TAG, "data: " + data.toString());
        super.onPushOpen(context, intent);
    }

    /**
     * Receiver of push notifications
     **/
    @Override
    protected void onPushReceive(Context context, Intent intent) {
        JSONObject data = getDataFromIntent(intent);
        // Do something with the data. To create a notification do:
        Log.i(TAG, "receieved push!!!");

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setContentTitle("Update!");
        try {
            builder.setContentText((CharSequence) data.get("alert"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        builder.setSmallIcon(R.drawable.logo_white);
        builder.setAutoCancel(true);

        // try getting specified fragment navigation data from push notification
        int fragmentPosition = 0;
        try {
            fragmentPosition = data.getInt("fragment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // upsert fragment navigation integer into intent
        switch(fragmentPosition % 4){
            case 0:
                intent.putExtra("fragment", 0);
                break;
            case 1:
                intent.putExtra("fragment", 1);
                break;
            case 2:
                intent.putExtra("fragment", 2);
                break;
            case 3:
                intent.putExtra("fragment", 3);
                break;
            default:
                intent.putExtra("fragment", 0);
                break;
        }
        
        // set activity for navigation
        intent.setClass(context, MainActivity.class);

        //  new PendingIntent for navigation on the push notification
        PendingIntent contentIntent;
        contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);

        // OPTIONAL create soundUri and set sound
//        builder.setSound(soundUri);

        // reload json files
        try {
            new CacheManager(PreferenceManager.getDefaultSharedPreferences(context)).updateAllJSONFiles();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // create push notification here
        notificationManager.notify("MyTag", 0, builder.build());
    }

    /**
     * parse data from push notification helper method
     **/
    private JSONObject getDataFromIntent(Intent intent) {
        JSONObject data = null;
        try {
            data = new JSONObject(intent.getExtras().getString(PARSE_DATA_KEY));
            Log.i(TAG, data.toString());
        } catch (JSONException e) {
            // Json was not readable...
            e.printStackTrace();
        }
        return data;
    }
}

