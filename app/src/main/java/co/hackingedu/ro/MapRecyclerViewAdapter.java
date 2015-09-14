package co.hackingedu.ro;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Info.MapInfo;

public class MapRecyclerViewAdapter extends RecyclerView.Adapter<MapRecyclerViewAdapter.MapViewHolder> {

    List<MapInfo> mapInfoList;

    static final int TYPE_MAP = 0;


    public MapRecyclerViewAdapter(List<MapInfo> mapInfoList) {
        this.mapInfoList = mapInfoList;
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

    public static class MapViewHolder extends RecyclerView.ViewHolder {

        public TextView map_num;
        public ImageView map_image;

        public MapViewHolder(View v) {
            super(v);
            map_num = (TextView) v.findViewById(R.id.map_num);
            map_image = (ImageView) v.findViewById(R.id.map);
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