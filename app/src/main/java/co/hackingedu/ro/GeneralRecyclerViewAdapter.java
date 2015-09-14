package co.hackingedu.ro;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Info.GeneralInfo;


public class GeneralRecyclerViewAdapter extends RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder> {

    List<GeneralInfo> generalInfoList;

    static final int TYPE_WIFI = 0;
    static final int TYPE_PROJECT = 1;
    static final int TYPE_PRIZE = 2;
    static final int TYPE_API = 3;

    public GeneralRecyclerViewAdapter(List<GeneralInfo> generalInfoList) {
        this.generalInfoList = generalInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_WIFI;
            case 1:
                return TYPE_PROJECT;
            case 2:
                return TYPE_PRIZE;
            case 3:
                return TYPE_API;
            default:
                return TYPE_API;
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
            case TYPE_WIFI: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.wifi_card, parent, false);
                return new GeneralViewHolder(view) {
                };
            }

            case TYPE_PROJECT: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.project_card, parent, false);
                return new GeneralViewHolder(view) {
                };
            }

            case TYPE_PRIZE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prize_card, parent, false);
                return new GeneralViewHolder(view) {
                };
            }

            case TYPE_API: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.api_card, parent, false);
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
            mText = (TextView) v.findViewById(R.id.wifi_name);

        }
    }

    @Override
    public void onBindViewHolder(GeneralViewHolder holder, int position) {

        //GeneralInfo ci = generalInfoList.get(position);
        //holder.mText.setText(ci.text);

        switch (getItemViewType(position)) {
            case TYPE_WIFI:
                break;
            case TYPE_PROJECT:
                break;
            case TYPE_PRIZE:
                break;
            case TYPE_API:
                break;
        }
    }
}