package co.hackingedu.ro.Activity;

/**
 * Created by Spicycurryman on 9/18/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import ViewAdapters.SponsorRecyclerViewAdapter;
import co.hackingedu.ro.Info.SponsorInfo;
import co.hackingedu.ro.R;


public class SponsorActivity extends Activity {

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

        SponsorInfo item1 = new SponsorInfo();
        item1.sponsor_name = "Twilio";
        item1.sponsor_url = "http://www.Twilio.com";
        mContentItems.add(item1);

        SponsorInfo item2 = new SponsorInfo();
        item2.sponsor_name = "Makeschool";
        item2.sponsor_url = "http://www.Makeschool.com";
        mContentItems.add(item2);

        SponsorInfo item3 = new SponsorInfo();
        item3.sponsor_name = "IBM";
        item3.sponsor_url = "http://www.IBM.com";
        mContentItems.add(item3);

        SponsorInfo item4 = new SponsorInfo();
        item4.sponsor_name = "Google";
        item4.sponsor_url = "http://www.google.com";
        mContentItems.add(item4);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(this, mRecyclerView, null);


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

