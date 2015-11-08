package com.contagotas.contagotas.activities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.contagotas.contagotas.R;
import com.contagotas.contagotas.activities.HistoricResultActivity;
import com.contagotas.contagotas.activities.SplashScreen;
import com.contagotas.contagotas.data.DAO.MediaGastos;

import java.util.List;

/**
 * Created by admin on 11/4/15.
 */
public class RecyclerViewHistoricAdapter extends RecyclerView.Adapter<RecyclerViewHistoricAdapter.ViewHolder> {

    List<MediaGastos> mListMediaGastos;
    private Context mcontext;
    public final static String TAG_POSITION = "position";

    public RecyclerViewHistoricAdapter(Context context, List<MediaGastos> listMediagastos){
        this.mListMediaGastos = listMediagastos;
        this.mcontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_card_view_historic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder recyclerViewHistoricViewHolder, final int i) {
        recyclerViewHistoricViewHolder.media.setText(mListMediaGastos.get(i).getTotal().toString());
        recyclerViewHistoricViewHolder.data.setText(mListMediaGastos.get(i).getData());
    }

    @Override
    public int getItemCount() {
        return mListMediaGastos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView media;
        TextView data;


        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_item_historic);
            media = (TextView)itemView.findViewById(R.id.txtMediaHistoricItem);
            data = (TextView)itemView.findViewById(R.id.txtDataHistoricItem);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(mcontext ,HistoricResultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(TAG_POSITION, position + 1);
            mcontext.startActivity(intent);
        }
    }

}
