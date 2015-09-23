package co.hackingedu.ro.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.hackingedu.ro.R;


public class ScheduleDetailFragment extends Fragment {
    Integer position;


    public ScheduleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_detail, container, false);

        //here is where I think you can pass the information between fragments

        //it works
        //now have to figure out how to do that based on itemID




        return view;
    }
}