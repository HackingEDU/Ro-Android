package co.hackingedu.ro;

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

import co.hackingedu.ro.Info.PrizeInfo;


public class PrizeActivity extends Activity {

    private List<PrizeInfo> mContentItems = new ArrayList<PrizeInfo>();
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

        mAdapter = new RecyclerViewMaterialAdapter(new PrizeRecyclerViewAdapter(mContentItems));



        PrizeInfo item1 = new PrizeInfo();
        item1.prize_name = "Macbook air";
        item1.prize_description = "200 GB";
        mContentItems.add(item1);

        PrizeInfo item2 = new PrizeInfo();
        item2.prize_name = "Macbook air";
        item2.prize_description = "200 GB";
        mContentItems.add(item2);

        PrizeInfo item3 = new PrizeInfo();
        item3.prize_name = "Macbook air";
        item3.prize_description = "200 GB";
        mContentItems.add(item3);

        PrizeInfo item4 = new PrizeInfo();
        item4.prize_name = "Macbook air";
        item4.prize_description = "200 GB";
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

