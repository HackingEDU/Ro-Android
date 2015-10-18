package co.hackingedu.ro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import org.json.JSONException;

import java.io.IOException;

import co.hackingedu.ro.Camera.CameraActivity;
import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.CacheManager;
import co.hackingedu.ro.fragment.FaqViewFragment;
import co.hackingedu.ro.fragment.MapViewFragment;
import co.hackingedu.ro.fragment.RecyclerViewFragment;
import co.hackingedu.ro.fragment.ScheduleViewFragment;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private MaterialViewPager mViewPager;

    private Toolbar toolbar;

    @Override
    protected void onRestart(){
        //super.onRestart();

//        Log.i(TAG, "reset!");
//
//        //setUpMaterialViewPager();
//        //CacheManager cacheManager = new CacheManager(PreferenceManager.getDefaultSharedPreferences(Activity.getContext()));
//        try {
//            Log.i(TAG, "initialize cacheManager");
//            //CacheManager cacheManager = new CacheManager(c);
//            CacheManager cacheManager =
//                    new CacheManager(
//                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
//            Log.i(TAG, "update Events");
//            cacheManager.updateJsonFile(cacheManager.EVENTS_FILE);
//
//            Log.i(TAG, "update FAQ");
//            cacheManager.updateJsonFile(cacheManager.FAQS_FILE);
//
//            Log.i(TAG, "update GENERAL");
//            cacheManager.updateJsonFile(cacheManager.GENERAL_FILE);
//
//            Log.i(TAG, "update Maps");
//            cacheManager.updateJsonFile(cacheManager.MAPS_FILE);
//
//            Log.i(TAG, "update Notifs");
//            cacheManager.updateJsonFile(cacheManager.NOTIFS_FILE);
//
//            Log.i(TAG, "update Prizes");
//            cacheManager.updateJsonFile(cacheManager.PRIZES_FILE);
//
//            Log.i(TAG, "update sponsors");
//            cacheManager.updateJsonFile(cacheManager.SPONSORS_FILE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.i(TAG, "new views");
//        setUpMaterialViewPager();
        Log.i(TAG, "restarted");

        super.onRestart();
    }

    @Override
    protected void onStart(){
        Log.i(TAG, "started");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.i(TAG, "resumed");
        super.onResume();
        
        // get intent and determine the fragment the push notification points to - default 0
        Intent intent = getIntent();
        int fragmentPosition = intent.getIntExtra("fragment", 0); // works
        mViewPager.getViewPager().setCurrentItem(fragmentPosition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("");

        reloadCards();

        // helper method to setup MaterialViewPager
        setUpMaterialViewPager();

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Have fun at HackingEDU :)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void reloadCards(){
        Log.i(TAG, "reset!");

        //setUpMaterialViewPager();
        //CacheManager cacheManager = new CacheManager(PreferenceManager.getDefaultSharedPreferences(Activity.getContext()));
        try {
            Log.i(TAG, "initialize cacheManager");
            //CacheManager cacheManager = new CacheManager(c);
            CacheManager cacheManager =
                    new CacheManager(
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
            Log.i(TAG, "update Events");
            cacheManager.updateJsonFile(cacheManager.EVENTS_FILE);

            Log.i(TAG, "update FAQ");
            cacheManager.updateJsonFile(cacheManager.FAQS_FILE);

            Log.i(TAG, "update GENERAL");
            cacheManager.updateJsonFile(cacheManager.GENERAL_FILE);

            Log.i(TAG, "update Maps");
            cacheManager.updateJsonFile(cacheManager.MAPS_FILE);

            Log.i(TAG, "update Notifs");
            cacheManager.updateJsonFile(cacheManager.NOTIFS_FILE);

            Log.i(TAG, "update Prizes");
            cacheManager.updateJsonFile(cacheManager.PRIZES_FILE);

            Log.i(TAG, "update sponsors");
            cacheManager.updateJsonFile(cacheManager.SPONSORS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "new views");
        setUpMaterialViewPager();
    }


    /**
     * Material View Pager Setup Helper method
     */
    private void setUpMaterialViewPager(){
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);

            }
        }


        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return RecyclerViewFragment.newInstance();
                    case 1:
                        return MapViewFragment.newInstance();
                    case 2:
                        return FaqViewFragment.newInstance();
                    case 3:
                        return ScheduleViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "General";
                    case 1:
                        return "Map";
                    case 2:
                        return "FAQ";
                    case 3:
                        return "Schedule";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());

        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_camera)
        {
            //open camera
            Intent i = new Intent(this, CameraActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
