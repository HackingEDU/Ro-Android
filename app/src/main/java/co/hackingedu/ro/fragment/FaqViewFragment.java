package co.hackingedu.ro.fragment;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

import ViewAdapters.FaqRecyclerViewAdapter;
import co.hackingedu.ro.Info.FaqInfo;
import co.hackingedu.ro.R;


public class FaqViewFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 4;

    public List<FaqInfo> mContentItems = new ArrayList<FaqInfo>();

    public static FaqViewFragment newInstance() {
        return new FaqViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewMaterialAdapter(new FaqRecyclerViewAdapter(mContentItems));


        FaqInfo item1 = new FaqInfo();
        item1.question = "Where/when to submit my hack?";
        item1.answer = "All hacks must submitted to ChallengePost\n" +
                "www.challengepost.com/HackingEDU by 9:30am\n" +
                " on Sunday in order to be eligible for prizes.";
        mContentItems.add(item1);

        FaqInfo item2 = new FaqInfo();
        item2.question = "Who can help me with coding? ";
        item2.answer = "Anyone with a HackingEDU Staff shirt can connect\n" +
                "you with a sponsor or experience team member \n" +
                "depending on the language or framework.";
        mContentItems.add(item2);

        FaqInfo item3 = new FaqInfo();
        item3.question = "Can someone help me with Git?";
        item3.answer = "Look for Vivek Vinodh, the tall,dark, and handsome \n" +
                "Indian guy. He is the Git master. He should be \n" +
                "sleeping in the stands right now.";
        mContentItems.add(item3);

        FaqInfo item4 = new FaqInfo();
        item4.question = "Can someone help me with Git?";
        item4.answer = "Look for Vivek Vinodh, the tall,dark, and handsome \n" +
                "Indian guy. He is the Git master. He should be \n" +
                "sleeping in the stands right now.";
        mContentItems.add(item4);


        mAdapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(mAdapter);


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }






}
