package co.hackingedu.ro.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.hackingedu.ro.R;


public class ScheduleDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";


    public ScheduleDetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_detail, container, false);

        return rootView;
    }
}