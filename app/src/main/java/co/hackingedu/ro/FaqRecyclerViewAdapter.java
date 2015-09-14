package co.hackingedu.ro;
/**
 * Created by Spicycurryman on 9/14/15.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.hackingedu.ro.Info.FaqInfo;

public class FaqRecyclerViewAdapter extends RecyclerView.Adapter<FaqRecyclerViewAdapter.FaqViewHolder> {

    List<FaqInfo> faqInfoList;
    static final int TYPE_FAQ = 0;

    public FaqRecyclerViewAdapter(List<FaqInfo> faqInfoList) {
        this.faqInfoList = faqInfoList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_FAQ;
        }

    }

    @Override
    public int getItemCount() {
        return faqInfoList.size();
    }

    @Override
    public FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case TYPE_FAQ: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.faq_card, parent, false);
                return new FaqViewHolder(view) {
                };
            }
        }
        return null;
    }

    public static class FaqViewHolder extends RecyclerView.ViewHolder {

        public TextView question;
        public TextView answer;

        public FaqViewHolder(View v) {
            super(v);
            question = (TextView) v.findViewById(R.id.question);
            answer = (TextView) v.findViewById(R.id.answer);
        }
    }

    @Override
    public void onBindViewHolder(FaqViewHolder holder, int position) {

        FaqInfo ci = faqInfoList.get(position);
        holder.question.setText(ci.question);
        holder.answer.setText(ci.answer);

        switch (getItemViewType(position)) {
            case TYPE_FAQ:
                break;
        }
    }
}