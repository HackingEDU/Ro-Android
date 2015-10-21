package co.hackingedu.ro.ViewAdapter;


import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.hackingedu.ro.Activity.PrizeActivity;
import co.hackingedu.ro.Activity.SponsorActivity;
import co.hackingedu.ro.Info.GeneralInfo;
import co.hackingedu.ro.R;


public class GeneralRecyclerViewAdapter extends RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder> {

    List<GeneralInfo> generalInfoList;

    static final int TYPE_WIFI = 0;
    static final int TYPE_PROJECT = 1;
    static final int TYPE_PRIZE = 2;
    static final int TYPE_API = 3;
    static final int TYPE_SHUTTLE = 4;

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
            case 4:
                return TYPE_SHUTTLE;
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
                        .inflate(R.layout.sponsor, parent, false);
                return new GeneralViewHolder(view) {
                };
            }

            case TYPE_SHUTTLE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.shuttle, parent, false);
                return new GeneralViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class GeneralViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mText;
        public CardView card_recycle;
        private final Context context;

        public GeneralViewHolder(View v) {
            super(v);
            mText = (TextView) v.findViewById(R.id.wifi_name);
            card_recycle = (CardView) v.findViewById(R.id.card_view);
            context = v.getContext();
            card_recycle.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(getAdapterPosition()==1){
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("wifi","cheggrocks");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Wifi Password copied to clipboard!", Toast.LENGTH_SHORT).show();
            }

            else if(getAdapterPosition()==3) {
                final Intent intent;
                intent = new Intent(context, PrizeActivity.class);
                context.startActivity(intent);
            }
            else if(getAdapterPosition()==4) {
                String url = "http://hackingedu.co/#sponsors";
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(i);
                }
            }
            else if(getAdapterPosition()==5) {

                String url = "http://www.hackingedu.co/live/#shuttles";
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(i);
                }

            }
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
            case TYPE_SHUTTLE:
                break;
        }
    }
}