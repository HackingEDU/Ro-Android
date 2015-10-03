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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import co.hackingedu.ro.Info.ScheduleInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.Activity.ScheduleDetailActivity;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ScheduleViewHolder> {

    private static final String TAG = "ScheduleRecyclerVA";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_SPEAKER_KEY = "name";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_IMAGE_KEY = "image";

    /**
     * String for referencing Intent Extras
     */
    private static String INTENT_EXTRA_ABOUT_KEY = "about";

    private List<ScheduleInfo> scheduleInfoList;
    static final int TYPE_SCHEDULE = 0;

    static JSONArray eventArray;

    /**
     * Constructor for ScheduleRecyclerViewAdapter
     * @param _scheduleInfoList Info List
     * @param _eventArray
     */
    public ScheduleRecyclerViewAdapter(List<ScheduleInfo> _scheduleInfoList, JSONArray _eventArray) {
        scheduleInfoList = _scheduleInfoList;
        eventArray = _eventArray;
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
            Log.i(TAG, "onclick");
            //you can use get adapter position to get the position of the recycler item
            //put Extra to save Data
            Intent i = new Intent(context, ScheduleDetailActivity.class);
            try {
                String speakerName = (String) ((JSONObject)
                        eventArray.get(getAdapterPosition()-1)).get(INTENT_EXTRA_SPEAKER_KEY);

                String speakerImage = (String) ((JSONObject)
                        eventArray.get(getAdapterPosition()-1)).get(INTENT_EXTRA_IMAGE_KEY);

                String speakerAbout = (String) ((JSONObject)
                        eventArray.get(getAdapterPosition()-1)).get(INTENT_EXTRA_ABOUT_KEY);

                Log.i(TAG, speakerName);
                Log.i(TAG, speakerImage);
                Log.i(TAG, speakerAbout);

                i.putExtra(INTENT_EXTRA_SPEAKER_KEY, speakerName);
                i.putExtra(INTENT_EXTRA_IMAGE_KEY, speakerImage);
                i.putExtra(INTENT_EXTRA_ABOUT_KEY, speakerAbout);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            context.startActivity(i);
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