package co.hackingedu.ro.ViewAdapter;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import co.hackingedu.ro.Activity.MapDetailActivity;
import co.hackingedu.ro.Activity.MapViewImageActivity;
import co.hackingedu.ro.Activity.ScheduleDetailActivity;
import co.hackingedu.ro.Info.MapInfo;
import co.hackingedu.ro.R;

public class MapRecyclerViewAdapter extends RecyclerView.Adapter<MapRecyclerViewAdapter.MapViewHolder> {
    private static final String TAG = "MapRecyclerViewAdapter";
    private static JSONArray mapArray;

    /**
     *
     */
    private static final String INTENT_EXTRA_MAP_KEY = "map_map";

    private static final String INTENT_EXTRA_IMAGE_KEY = "map_img";

    List<MapInfo> mapInfoList;

    static final int TYPE_MAP = 0;


    public MapRecyclerViewAdapter(List<MapInfo> _mapInfoList, JSONArray _mapArray) {
        mapArray = _mapArray;
        mapInfoList = _mapInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_MAP;
        }

    }

    @Override
    public int getItemCount() {
        return mapInfoList.size();
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_MAP: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.map_card, parent, false);
                return new MapViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView map_num;
        public ImageView map_image;
        public CardView map_card_view;
        private final Context context;

        public MapViewHolder(View v) {
            super(v);
            map_num = (TextView) v.findViewById(R.id.map_num);
            map_image = (ImageView) v.findViewById(R.id.map);
            map_card_view = (CardView) v.findViewById(R.id.map_card_view);
            context = v.getContext();

            map_card_view.setOnClickListener(this);
        }

        /**
         * onClick Map event
         * @param v view that was clicked
         */
        @Override
        public void onClick(View v) {
            Log.i(TAG, "onclick");
            //you can use get adapter position to get the position of the recycler item
            //put Extra to save Data
            Intent i = new Intent(context, MapViewImageActivity.class);
            try {

                String mapImage = (String) ((JSONObject)
                        mapArray.get(getAdapterPosition()-1)).get(INTENT_EXTRA_IMAGE_KEY);

                String mapMap = (String) ((JSONObject)
                        mapArray.get(getAdapterPosition()-1)).get(INTENT_EXTRA_MAP_KEY);

                Log.i(TAG, mapImage);
                Log.i(TAG, mapMap);

                i.putExtra(INTENT_EXTRA_IMAGE_KEY, mapImage);
                i.putExtra(INTENT_EXTRA_MAP_KEY, mapMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            context.startActivity(i);
        }
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {

        //GeneralInfo ci = generalInfoList.get(position);
        //holder.mText.setText(ci.text);

        MapInfo ci = mapInfoList.get(position);
        holder.map_num.setText(ci.map_text);
        //figure out how to set holder for map image

        switch (getItemViewType(position)) {
            case TYPE_MAP:
                break;
        }
    }
}