package com.saroshmadara.root.premiotravelsandtours.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryVisa;

import java.util.ArrayList;

/**
 * Created by root on 8/18/16.
 */
public class CountryVisaAdapter extends RecyclerView.Adapter<CountryVisaAdapter.CountryVisaViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<CountryVisa> data;
    private ArrayList<CountryVisa> temp;

    // setting up listener
    private OnVisaItemClickListener mListener;

    public CountryVisaAdapter(Context ctx,ArrayList<CountryVisa> visas) {
        data = visas;
        mContext =ctx;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public CountryVisaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.visa_item,parent,false);

        CountryVisaViewHolder holder = new CountryVisaViewHolder(view);

        return holder;
    }

    public void setOnVisaItemClickListener(OnVisaItemClickListener listener){
        mListener = listener;
    }


    @Override
    public void onBindViewHolder(CountryVisaViewHolder holder, int position) {
        CountryVisa visa = data.get(position);
        holder.mFlagName.setText(visa.getName());
        holder.mFlagIcon.setImageResource(visa.getFlagId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CountryVisaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private ImageView mFlagIcon;
        private TextView mFlagName;

        public CountryVisaViewHolder(View itemView) {
            super(itemView);

            mFlagIcon = (ImageView) itemView.findViewById(R.id.flagIcon);
            mFlagName = (TextView) itemView.findViewById(R.id.flagName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onVisaItemClick(data,v,getAdapterPosition());
            }
        }
    }

    interface OnVisaItemClickListener{
        public void onVisaItemClick(ArrayList<CountryVisa> data,View view,int position);
    }
}
