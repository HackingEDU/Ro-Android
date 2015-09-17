package co.hackingedu.ro.fragment;

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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import co.hackingedu.ro.GeneralRecyclerViewAdapter;
import co.hackingedu.ro.Info.GeneralInfo;
import co.hackingedu.ro.R;
import co.hackingedu.ro.backend.BackendManager;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    /**
     * BackendManager to handle API Calls
     */
    private BackendManager backendManager;

    /**
     * JSONArray field to store response from backendManager
     */
    private JSONArray generalArray;

    /**
     * [{"_id":"55f66d217cf6bb4bbc4554a5",
     * "handle":"main",
     * "wifi":{"ssid":"HackingEDUwifi","pw":"yourFuckingMama"},
     * "submission":{"time":"4:20pm","link":"http://url.com/to/the/submission/page"},
     * "prizes":[
     *      {"company":"Twilio","name":"Best Use of the Twilio Api","description":"1 donkey"},
     *      {"company":"Chegg","name":"Best Use of Chegg API","description":"1 ipad, 2 donkeys, 3 pickles"}
     *      ],
     * "sponsors":[
     *      {"name":"Twilio","url":"http://twilio.com","img":"../path/to/img.png"},
     *      {"name":"Chegg","url":"http://chegg.com","img":"../path/to/img.png"}
     *      ]}]
     */

    /** final string for querying question    */
    private final String HANDLE_QUERY = "handle";

    /** final string for querying wifi     */
    private final String WIFI_QUERY = "wifi";

    /** final string for querying ssid     */
    private final String WIFI_SSID_QUERY = "ssid";

    /** final string for querying answer*/
    private final String WIFI_PASSWORD_QUERY = "pw";

    /** final string for querying submission     */
    private final String SUBMISSION_QUERY = "submission";

    /** final string for querying time     */
    private final String SUBMISSION_TIME_QUERY = "time";

    /** final string for querying link     */
    private final String SUBMISSION_LINK_QUERY = "link";

    /** final string for querying prizes    */
    private final String PRIZES_QUERY = "prizes";

    /** final string for querying company    */
    private final String PRIZES_COMPANY_QUERY = "company";

    /** final string for querying name   */
    private final String PRIZES_NAME_QUERY = "name";

    /** final string for querying description */
    private final String PRIZES_DESCRIPTION_QUERY = "description";

    /** final string for querying sponsors    */
    private final String SPONSORS_QUERY = "sponsors";

    /** final string for querying company    */
    private final String SPONSORS_NAME_QUERY = "name";

    /** final string for querying url   */
    private final String SPONSORS_URL_QUERY = "url";

    /** final string for querying image */
    private final String SPONSORS_IMG_QUERY = "img";

    public RecyclerView mRecyclerView;
    public RecyclerViewMaterialAdapter mAdapter;
    public static final int ITEM_COUNT = 4;

    public List<GeneralInfo> mContentItems = new ArrayList<GeneralInfo>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
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

        mAdapter = new RecyclerViewMaterialAdapter(new GeneralRecyclerViewAdapter(mContentItems));


            for (int i = 0; i < ITEM_COUNT; ++i) {
                GeneralInfo item = new GeneralInfo();
                mContentItems.add(item);
            }

        mAdapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(mAdapter);


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }






}