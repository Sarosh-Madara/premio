package com.saroshmadara.root.premiotravelsandtours.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saroshmadara.root.premiotravelsandtours.model.DealsAndPkgs;

import java.util.ArrayList;

/**
 * Created by root on 8/19/16.
 */
public class LatestPkgAndDealsAdapter extends RecyclerView.Adapter<LatestPkgAndDealsAdapter.LatestPkgAndDealsViewHolder>{

    private LatestPkgAndDealsOnClickListener mListener;

    private LayoutInflater mLayoutInflater;
    private ArrayList<DealsAndPkgs> data;

    public LatestPkgAndDealsAdapter(Context ctx, ArrayList<DealsAndPkgs> data) {
        mLayoutInflater = LayoutInflater.from(ctx);
        this.data = data;
    }

    @Override
    public LatestPkgAndDealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        return null;
    }

    public void setOnLatestPkgAndDealClickListener(LatestPkgAndDealsOnClickListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(LatestPkgAndDealsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class LatestPkgAndDealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private LayoutInflater mLayoutInflater;


        public LatestPkgAndDealsViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onLatestPkgAndDealsClick(data,v,getAdapterPosition());
        }
    }


    interface LatestPkgAndDealsOnClickListener{
        public void onLatestPkgAndDealsClick(ArrayList<DealsAndPkgs> data,View v, int position);
    }

}
