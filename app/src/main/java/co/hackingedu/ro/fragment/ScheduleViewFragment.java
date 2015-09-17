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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.Info.ScheduleInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.ScheduleRecyclerViewAdapter;
import co.hackingedu.ro.backend.BackendManager;

/**
 * Fragment for displaying Schedule View
 */
public class ScheduleViewFragment extends Fragment {

    /**
     * BackendManager to handle API Calls
     * Utilize JsonManager to pull Strings out of returned Arrays
     */
    private BackendManager backendManager;

    private JSONArray eventArray;

    private final String NAME_QUERY = "name";
    private final String TIME_QUERY = "time";
    private final String LOCATION_QUERY = "location";

    /**
     *
     */
    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 4;

    public List<ScheduleInfo> mContentItems = new ArrayList<ScheduleInfo>();

    public static ScheduleViewFragment newInstance() {
        return new ScheduleViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // instantiate backendManager to begin api calls!
        backendManager = new BackendManager();
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

        // save local copy of JSON arrays from backend Manager
        try {
            eventArray = (JSONArray) backendManager.get(backendManager.EVENTS_ENDPOINT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // loop through each event in JSON Array and do frontend stuff!
        for (int i = 0; i < eventArray.length(); i++)
        {
            ScheduleInfo item = new ScheduleInfo();
            try {
                // parsing array into String
                item.schedule_event = (String) ((JSONObject) eventArray.get(i)).get(NAME_QUERY);
                item.time_location = (String) ((JSONObject) eventArray.get(i)).get(TIME_QUERY)
                        + " | " + (String) ((JSONObject) eventArray.get(i)).get(LOCATION_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentItems.add(item);
        }

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
