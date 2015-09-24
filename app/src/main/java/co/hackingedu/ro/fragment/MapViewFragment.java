package co.hackingedu.ro.fragment;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import co.hackingedu.ro.Info.MapInfo;
import co.hackingedu.ro.ViewAdapter.MapRecyclerViewAdapter;
import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.BackendManager;


public class MapViewFragment extends Fragment {

        /**
         * BackendManager to handle API Calls
         */
        private BackendManager backendManager;

        /**
         * JSONArray field to store response from backendManager
         */
        private JSONArray mapsArray;

        /**
         * final string for querying map
         */
        private final String MAP_QUERY = "map";

        /**
         * final string for querying image
         */
        private final String IMAGE_QUERY = "image";

    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 3;

    public List<MapInfo> mContentItems = new ArrayList<MapInfo>();

    public static MapViewFragment newInstance() {
        return new MapViewFragment();
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

        mAdapter = new RecyclerViewMaterialAdapter(new MapRecyclerViewAdapter(mContentItems));

        // save local copy of JSON arrays from backend Manager
        try {
            mapsArray = (JSONArray) backendManager.get(backendManager.EVENTS_ENDPOINT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // loop through each map in JSON Array and do frontend stuff!
        for (int i = 0; i < mapsArray.length(); i++)
        {
            MapInfo item = new MapInfo();
            try {
                // parsing array into String
                item.map_text = (String) ((JSONObject) mapsArray.get(i)).get(MAP_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentItems.add(item);
        }

        MapInfo item1 = new MapInfo();
        item1.map_text = "Map 1";
        mContentItems.add(item1);

        MapInfo item2 = new MapInfo();
        item2.map_text = "Map 2";
        mContentItems.add(item2);

        MapInfo item3 = new MapInfo();
        item3.map_text = "Map 3";
        mContentItems.add(item3);


        mAdapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(mAdapter);


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }






}
