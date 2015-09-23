package ViewAdapters;
/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Activity.ScheduleDetailActivity;
import co.hackingedu.ro.Info.ScheduleInfo;
import co.hackingedu.ro.R;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ScheduleViewHolder> {

    List<ScheduleInfo> scheduleInfoList;
    static final int TYPE_SCHEDULE = 0;

    public ScheduleRecyclerViewAdapter(List<ScheduleInfo> scheduleInfoList) {
        this.scheduleInfoList = scheduleInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_SCHEDULE;
        }
    }

    @Override
    public int getItemCount() {
        return scheduleInfoList.size();
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_SCHEDULE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.schedule_card, parent, false);
                return new ScheduleViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView schedule_event;
        public TextView time_location;
        public CardView schedule_card_view;
        private final Context context;


        public ScheduleViewHolder(View v) {
            super(v);
            schedule_event = (TextView) v.findViewById(R.id.schedule_event);
            time_location = (TextView) v.findViewById(R.id.time_location);
            schedule_card_view = (CardView) v.findViewById(R.id.schedule_card_view);

            context = v.getContext();

            schedule_card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            //you can use get adapter position to get the position of the recycler item


            //put Extra to save Data
            Intent i = new Intent(context, ScheduleDetailActivity.class);
            i.putExtra("lol", getAdapterPosition());

            intent =  new Intent(context, ScheduleDetailActivity.class);
            context.startActivity(intent);

        }
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {

        ScheduleInfo ci = scheduleInfoList.get(position);
        holder.schedule_event.setText(ci.schedule_event);
        holder.time_location.setText(ci.time_location);


        switch (getItemViewType(position)) {
            case TYPE_SCHEDULE:
                break;
        }
    }
}