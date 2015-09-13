package co.hackingedu.ro;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Info.GeneralInfo;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class GeneralRecyclerViewAdapter extends RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder> {

    List<GeneralInfo> generalInfoList;

    static final int TYPE_CELL = 1;


    public GeneralRecyclerViewAdapter(List<GeneralInfo> generalInfoList) {
        this.generalInfoList = generalInfoList;
    }



    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return generalInfoList.size();
    }

    @Override
    public GeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new GeneralViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class GeneralViewHolder extends RecyclerView.ViewHolder {

        public TextView mText;

        public GeneralViewHolder(View v) {
            super(v);
            mText = (TextView) v.findViewById(R.id.textView);

        }
    }


    @Override
    public void onBindViewHolder(GeneralViewHolder holder, int position) {

        GeneralInfo ci = generalInfoList.get(position);
        holder.mText.setText(ci.text);

        switch (getItemViewType(position)) {
            case TYPE_CELL:
                break;
        }
    }
}