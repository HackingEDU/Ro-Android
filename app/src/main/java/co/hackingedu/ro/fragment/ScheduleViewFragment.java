package co.hackingedu.ro.fragment;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
<<<<<<< HEAD
=======

>>>>>>> e4f9896... begin replacing JSONManager
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.Info.ScheduleInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.ScheduleRecyclerViewAdapter;
import co.hackingedu.ro.backend.BackendManager;
import co.hackingedu.ro.backend.JsonManager;

/**
 * Fragment for displaying Schedule View
 */
public class ScheduleViewFragment extends Fragment {

    /**
     * BackendManager to handle API Calls
<<<<<<< HEAD
     */
    private BackendManager backendManager;

    /**
     * JSONArray field to store response from backendManager
     */
    private JSONArray eventArray;

    /**
     * final string for querying name
     */
    private final String NAME_QUERY = "name";

    /**
     * final string for querying time
     */
    private final String TIME_QUERY = "time";

    /**
     * final string for querying location
     */
    private final String LOCATION_QUERY = "location";

    /**
     * final string for querying day
     */
    private final String DAY_QUERY = "day";

    /**
     * final string for querying image
=======
     * Utilize JsonManager to pull Strings out of returned Arrays
     */
    private BackendManager backendManager;

    private JSONArray eventArray;

    private final String NAME_QUERY = "name";
    private final String TIME_QUERY = "time";
    private final String LOCATION_QUERY = "location";

    /**
     *
>>>>>>> e4f9896... begin replacing JSONManager
     */
    private final String IMAGE_QUERY = "image";

    /**
     * final string for querying about
     */
    private final String ABOUT_QUERY = "about";

    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 4; // not really necessary because JSONArray has length()

    public List<ScheduleInfo> mContentItems = new ArrayList<ScheduleInfo>();

    public static ScheduleViewFragment newInstance() {
        return new ScheduleViewFragment();
    }

    @Override
<<<<<<< HEAD
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // instantiate backendManager to begin api calls!
        backendManager = new BackendManager();
=======
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        backendManager = new BackendManager();

        try {
            Log.i("ScheduleViewFragment", "starting getting");
            Log.i("ScheduleViewFragment", "post getting: "
                    + new JsonManager()
                    .get("name", 0, (JSONArray) backendManager.get(backendManager.EVENTS_ENDPOINT)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
>>>>>>> e4f9896... begin replacing JSONManager
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewMaterialAdapter(new ScheduleRecyclerViewAdapter(mContentItems));

<<<<<<< HEAD
        // save local copy of JSON arrays from backend Manager
=======
>>>>>>> e4f9896... begin replacing JSONManager
        try {
            eventArray = (JSONArray) backendManager.get(backendManager.EVENTS_ENDPOINT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD

        // loop through each event in JSON Array and do frontend stuff!
=======
>>>>>>> e4f9896... begin replacing JSONManager
        for (int i = 0; i < eventArray.length(); i++)
        {
            ScheduleInfo item = new ScheduleInfo();
            try {
<<<<<<< HEAD
                // parsing array into String
                item.schedule_event = (String) ((JSONObject) eventArray.get(i)).get(NAME_QUERY);
                item.time_location = (String) ((JSONObject) eventArray.get(i)).get(TIME_QUERY)
                        + " | " + (String) ((JSONObject) eventArray.get(i)).get(LOCATION_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentItems.add(item);
        }

        // static examples
=======
                item.schedule_event = (String) ((JSONObject) eventArray.get(i)).get(NAME_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            item.time_location = "9:00 a.m. - 4:00 p.m | Reception Desk";
            mContentItems.add(item);
        }

>>>>>>> e4f9896... begin replacing JSONManager
        ScheduleInfo item1 = new ScheduleInfo();
        item1.schedule_event = "Badge Pickup";
        item1.time_location = "9:00 a.m. - 4:00 p.m | Reception Desk";
        mContentItems.add(item1);

        ScheduleInfo item2 = new ScheduleInfo();
        item2.schedule_event = "Breakfast";
        item2.time_location = "9:30 a.m. - 10:45 a.m. | Main Lobby";
        mContentItems.add(item2);

        ScheduleInfo item3 = new ScheduleInfo();
        item3.schedule_event = "How to GIT all the ladies";
        item3.time_location = "11:30 a.m. - 12:30 p.m. | Tigga Room";
        mContentItems.add(item3);

        ScheduleInfo item4 = new ScheduleInfo();
        item4.schedule_event = "Building non-responsive sites";
        item4.time_location = "1:00 p.m. - 1:45 p.m. | Vivek’s World";
        mContentItems.add(item4);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }
}
