package ViewAdapters;

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
import co.hackingedu.ro.Info.SponsorInfo;
import co.hackingedu.ro.R;

public class SponsorRecyclerViewAdapter extends RecyclerView.Adapter<SponsorRecyclerViewAdapter.SponsorViewHolder> {

    List<SponsorInfo> sponsorInfoList;
    static final int TYPE_SPONSOR = 0;

    public SponsorRecyclerViewAdapter(List<SponsorInfo> sponsorInfoList) {
        this.sponsorInfoList = sponsorInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_SPONSOR;
        }

    }

    @Override
    public int getItemCount() {
        return sponsorInfoList.size();
    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_SPONSOR: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.sponsor_card_recycle, parent, false);
                return new SponsorViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class SponsorViewHolder extends RecyclerView.ViewHolder   {

        public ImageView sponsor_image;
        public CardView cardView;
        public TextView sponsor_name;
        public TextView sponsor_link;

        private final Context context;


        public SponsorViewHolder(View v) {
            super(v);
            sponsor_image = (ImageView) v.findViewById(R.id.sponsor_image);
            sponsor_name = (TextView) v.findViewById(R.id.sponsor_name);
            sponsor_link = (TextView) v.findViewById(R.id.sponsor_link);
            cardView = (CardView) v.findViewById(R.id.card_view);
            context = v.getContext();


        }

    }

    @Override
    public void onBindViewHolder(SponsorViewHolder holder, int position) {

        //GeneralInfo ci = generalInfoList.get(position);
        //holder.mText.setText(ci.text);

        SponsorInfo ci = sponsorInfoList.get(position);
        holder.sponsor_name.setText(ci.sponsor_name);
        holder.sponsor_link.setText(ci.sponsor_url);
        //figure out how to set holder for map image
        holder.sponsor_image.setImageDrawable(ci.sponsor_image);

        switch (getItemViewType(position)) {
            case TYPE_SPONSOR:
                break;
        }
    }
}