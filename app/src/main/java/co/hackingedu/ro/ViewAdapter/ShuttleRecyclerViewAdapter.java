package co.hackingedu.ro.ViewAdapter;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Info.ShuttleInfo;
import co.hackingedu.ro.R;

public class ShuttleRecyclerViewAdapter extends RecyclerView.Adapter<ShuttleRecyclerViewAdapter.ShuttleViewHolder> {

    List<ShuttleInfo> shuttleInfoList;

    static final int TYPE_SHUTTLE = 0;


    public ShuttleRecyclerViewAdapter(List<ShuttleInfo> shuttleInfoList) {
        this.shuttleInfoList = shuttleInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_SHUTTLE;
        }

    }

    @Override
    public int getItemCount() {
        return shuttleInfoList.size();
    }

    @Override
    public ShuttleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_SHUTTLE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.shuttle_recycle_view, parent, false);
                return new ShuttleViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class ShuttleViewHolder extends RecyclerView.ViewHolder   {

        public CardView shuttle_card_recycle;
        public TextView college_name;
        private final Context context;


        public ShuttleViewHolder(View v) {
            super(v);
            college_name= (TextView) v.findViewById(R.id.college_name);
            shuttle_card_recycle = (CardView) v.findViewById(R.id.shuttle_recycle);

            context = v.getContext();
        }
    }

    @Override
    public void onBindViewHolder(ShuttleViewHolder holder, int position) {

        //GeneralInfo ci = generalInfoList.get(position);
        //holder.mText.setText(ci.text);

        ShuttleInfo ci = shuttleInfoList.get(position);
        holder.college_name.setText(ci.college_name);

        switch (getItemViewType(position)) {
            case TYPE_SHUTTLE:
                break;
        }
    }
}