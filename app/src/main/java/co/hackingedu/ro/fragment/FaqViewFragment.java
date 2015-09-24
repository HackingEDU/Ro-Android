package co.hackingedu.ro.fragment;

/**
 * Created by Spicycurryman on 9/14/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.ViewAdapter.FaqRecyclerViewAdapter;
import co.hackingedu.ro.Info.FaqInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.BackendManager;
import co.hackingedu.ro.backend.CacheManager;


public class FaqViewFragment extends Fragment {
    private final String TAG = "FAQViewFragment";

    /**
     * BackendManager to handle API Calls
     */
    private BackendManager backendManager;

    /**
     * JSONArray field to store response from backendManager
     */
    private JSONArray faqsArray;

    private CacheManager cacheManager;

    /**
     * final string for querying question
     */
    private final String QUESTION_QUERY = "q";

    /**
     * final string for querying answer
     */
    private final String ANSWER_QUERY = "a";

    private boolean updateLater;

    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 4;

    public List<FaqInfo> mContentItems = new ArrayList<FaqInfo>();

    public static FaqViewFragment newInstance() {
        return new FaqViewFragment();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Log.i(TAG, "instantiating cacheManger");
//            cacheManager = new CacheManager(cacheManager.FAQS_FILE, context);
        cacheManager = new CacheManager(PreferenceManager.getDefaultSharedPreferences(context));
        Log.i(TAG, "cacheManager success");

        // pull from local storage for quick loading
        try {
            if(cacheManager.fileIsNull(cacheManager.FAQS_FILE)){
                updateLater = false;
                cacheManager.updateJsonFile(cacheManager.FAQS_FILE);
                Log.i(TAG, "updateLater status: " + updateLater);
            } else {
                // we need to update later!!!!
                updateLater = true;
                Log.i(TAG, "updateLater status: " + updateLater);
            }
            faqsArray = cacheManager.getJsonArray(cacheManager.FAQS_FILE, context);
        } catch (JSONException e) {
            Log.i(TAG, "JSON Exception: onCreateView 2");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i(TAG, "IO Exception: onCreateView 2");
            e.printStackTrace();
        }
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

        // save local copy of JSON arrays from backend Manager
//        try {
//            faqsArray = (JSONArray) backendManager.get(backendManager.FAQS_ENDPOINT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        // loop through each faq in JSON Array and do frontend stuff!
        for (int i = 0; i < faqsArray.length(); i++)
        {
            FaqInfo item = new FaqInfo();
            try {
                // parsing array into String
                item.question = (String) ((JSONObject) faqsArray.get(i)).get(QUESTION_QUERY);
                item.answer = (String) ((JSONObject) faqsArray.get(i)).get(ANSWER_QUERY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mContentItems.add(item);
        }

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

        /****************************************************************
         *
         *
         * REMEMBER TO UPDATE THE INFO AFTER THIS!!!!!!!!!!!!!!!!!!!!!
         *
         *
         ****************************************************************/
    }
}
