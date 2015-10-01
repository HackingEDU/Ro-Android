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

import co.hackingedu.ro.Info.PrizeInfo;
import co.hackingedu.ro.R;

public class PrizeRecyclerViewAdapter extends RecyclerView.Adapter<PrizeRecyclerViewAdapter.PrizeViewHolder> {

    List<PrizeInfo> prizeInfoList;

    static final int TYPE_PRIZE = 0;


    public PrizeRecyclerViewAdapter(List<PrizeInfo> prizeInfoList) {
        this.prizeInfoList = prizeInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_PRIZE;
        }

    }

    @Override
    public int getItemCount() {
        return prizeInfoList.size();
    }

    @Override
    public PrizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_PRIZE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prize_card_recycle, parent, false);
                return new PrizeViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class PrizeViewHolder extends RecyclerView.ViewHolder   {

        public ImageView sponsor_image;
        public CardView prize_card_recycle;
        public TextView prize_name;
        public TextView prize_desc;

        private final Context context;


        public PrizeViewHolder(View v) {
            super(v);
            sponsor_image = (ImageView) v.findViewById(R.id.sponsor_image);
            prize_name = (TextView) v.findViewById(R.id.prize_name);
            prize_desc = (TextView) v.findViewById(R.id.prize_desc);
            prize_card_recycle = (CardView) v.findViewById(R.id.prize_card_recycle);
            context = v.getContext();
        }
    }

    @Override
    public void onBindViewHolder(PrizeViewHolder holder, int position) {

        //GeneralInfo ci = generalInfoList.get(position);
        //holder.mText.setText(ci.text);

        PrizeInfo ci = prizeInfoList.get(position);
        holder.prize_name.setText(ci.prize_name);
        holder.prize_desc.setText(ci.prize_description);
        //figure out how to set holder for map image
        holder.sponsor_image.setImageDrawable(ci.sponsor_image);

        switch (getItemViewType(position)) {
            case TYPE_PRIZE:
                break;
        }
    }
}