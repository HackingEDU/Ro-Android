package co.hackingedu.ro.Activity;

/**
 * Created by Spicycurryman on 9/18/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.Info.MapInfo;
import co.hackingedu.ro.Info.SponsorInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.ViewAdapter.SponsorRecyclerViewAdapter;
import co.hackingedu.ro.backend.CacheManager;


public class SponsorActivity extends Activity {

    private final String TAG = "SponsorActivity";

    /**
     * final string for querying sponsor names
     */
    private final String SPONSORS_NAME_QUERY = "name";

    private final String SPONSORS_URL_QUERY = "url";

    private CacheManager cacheManager;

    private boolean updateLater;

    /**
     * JSONArray field to store response from backendManager
     */
    private JSONArray sponsorArray;

    private List<SponsorInfo> mContentItems = new ArrayList<SponsorInfo>();
    private RecyclerView mRecyclerView;
    private RecyclerViewMaterialAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recyclerview);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewMaterialAdapter(new SponsorRecyclerViewAdapter(mContentItems));

        populateCards();

//        SponsorInfo item1 = new SponsorInfo();
//        item1.sponsor_name = "Twilio";
//        item1.sponsor_url = "http://www.Twilio.com";
//        mContentItems.add(item1);
//
//        SponsorInfo item2 = new SponsorInfo();
//        item2.sponsor_name = "Makeschool";
//        item2.sponsor_url = "http://www.Makeschool.com";
//        mContentItems.add(item2);
//
//        SponsorInfo item3 = new SponsorInfo();
//        item3.sponsor_name = "IBM";
//        item3.sponsor_url = "http://www.IBM.com";
//        mContentItems.add(item3);
//
//        SponsorInfo item4 = new SponsorInfo();
//        item4.sponsor_name = "Google";
//        item4.sponsor_url = "http://www.google.com";
//        mContentItems.add(item4);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(this, mRecyclerView, null);


    }

    private void populateCards(){
        Log.i(TAG, "instantiating cacheManger");
//            cacheManager = new CacheManager(cacheManager.FAQS_FILE, context);
        cacheManager = new CacheManager(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        Log.i(TAG, "cacheManager success");

        // pull from local storage for quick loading
        try {
//            if(cacheManager.fileIsNull(cacheManager.EVENTS_FILE)){
//                updateLater = false;
//                cacheManager.updateJsonFile(cacheManager.EVENTS_FILE);
//                Log.i(TAG, "updateLater status: " + updateLater);
//            } else {
//                // we need to update later!!!!
//                updateLater = true;
//                Log.i(TAG, "updateLater status: " + updateLater);
//            }
            sponsorArray = cacheManager.getJsonArray(cacheManager.SPONSORS_FILE, getApplicationContext());
        } catch (JSONException e) {
            Log.i(TAG, "JSON Exception: onCreateView 2");
            e.printStackTrace();
        }

        // loop through each sponsor in JSON Array and do frontend stuff!
        for (int i = 0; i < sponsorArray.length(); i++)
        {
            SponsorInfo item = new SponsorInfo();
            try {
                // parsing array into String
                item.sponsor_name = (String) ((JSONObject) sponsorArray.get(i)).get(SPONSORS_NAME_QUERY);
                item.sponsor_url = (String) ((JSONObject) sponsorArray.get(i)).get(SPONSORS_URL_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentItems.add(item);
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


}
