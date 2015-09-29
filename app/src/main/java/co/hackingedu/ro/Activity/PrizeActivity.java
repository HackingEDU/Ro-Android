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

import co.hackingedu.ro.Info.PrizeInfo;
import co.hackingedu.ro.Info.SponsorInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.ViewAdapter.PrizeRecyclerViewAdapter;
import co.hackingedu.ro.backend.CacheManager;


public class PrizeActivity extends Activity {

    private static final String TAG = "PrizeActivity";
    private static final String PRIZE_COMPANY_QUERY = "company";
    private static final String PRIZE_DESCRIPTION_QUERY = "description";

    private List<PrizeInfo> mContentItems = new ArrayList<PrizeInfo>();
    private RecyclerView mRecyclerView;
    private RecyclerViewMaterialAdapter mAdapter;
    private CacheManager cacheManager;
    private boolean updateLater;
    private JSONArray prizeArray;

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

        mAdapter = new RecyclerViewMaterialAdapter(new PrizeRecyclerViewAdapter(mContentItems));

        populateCards();

//        PrizeInfo item1 = new PrizeInfo();
//        item1.prize_name = "Macbook air";
//        item1.prize_description = "200 GB";
//        mContentItems.add(item1);
//
//        PrizeInfo item2 = new PrizeInfo();
//        item2.prize_name = "Macbook air";
//        item2.prize_description = "200 GB";
//        mContentItems.add(item2);
//
//        PrizeInfo item3 = new PrizeInfo();
//        item3.prize_name = "Macbook air";
//        item3.prize_description = "200 GB";
//        mContentItems.add(item3);
//
//        PrizeInfo item4 = new PrizeInfo();
//        item4.prize_name = "Macbook air";
//        item4.prize_description = "200 GB";
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
//            if(cacheManager.fileIsNull(cacheManager.PRIZES_FILE)){
//                updateLater = false;
//                cacheManager.updateJsonFile(cacheManager.PRIZES_FILE);
//                Log.i(TAG, "updateLater status: " + updateLater);
//            } else {
//                // we need to update later!!!!
//                updateLater = true;
//                Log.i(TAG, "updateLater status: " + updateLater);
//            }
            prizeArray = cacheManager.getJsonArray(cacheManager.PRIZES_FILE, getApplicationContext());
        } catch (JSONException e) {
            Log.i(TAG, "JSON Exception: onCreateView 2");
            e.printStackTrace();
        }

        // loop through each sponsor in JSON Array and do frontend stuff!
        for (int i = 0; i < prizeArray.length(); i++)
        {
            PrizeInfo item = new PrizeInfo();
            try {
                // parsing array into String
                item.prize_name = (String) ((JSONObject) prizeArray.get(i)).get(PRIZE_COMPANY_QUERY);
                item.prize_description = (String) ((JSONObject) prizeArray.get(i)).get(PRIZE_DESCRIPTION_QUERY);
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
