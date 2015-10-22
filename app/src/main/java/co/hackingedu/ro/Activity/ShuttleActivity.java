package co.hackingedu.ro.Activity;

/**
 * Created by Spicycurryman on 9/18/15.
 */

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.Info.ShuttleInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.ViewAdapter.ShuttleRecyclerViewAdapter;


public class ShuttleActivity extends Activity {

    private List<ShuttleInfo> mContentItems = new ArrayList<ShuttleInfo>();
    private RecyclerView mRecyclerView;
    private RecyclerViewMaterialAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.fragment_recyclerview);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewMaterialAdapter(new ShuttleRecyclerViewAdapter(mContentItems));

        ShuttleInfo item1 = new ShuttleInfo();
        item1.college_name = "UC Davis";
        mContentItems.add(item1);

        ShuttleInfo item2 = new ShuttleInfo();
        item2.college_name = "UC Berkeley";
        mContentItems.add(item2);

        ShuttleInfo item3 = new ShuttleInfo();
        item3.college_name = "Stanford";
        mContentItems.add(item3);

        ShuttleInfo item4 = new ShuttleInfo();
        item4.college_name = "San Jose State";
        mContentItems.add(item4);

        ShuttleInfo item5 = new ShuttleInfo();
        item5.college_name = "UC Santa Cruz";
        mContentItems.add(item5);

        ShuttleInfo item6 = new ShuttleInfo();
        item6.college_name = "UCLA";
        mContentItems.add(item6);

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
