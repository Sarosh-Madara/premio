package com.saroshmadara.root.premiotravelsandtours.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryPackage;
import com.saroshmadara.root.premiotravelsandtours.rest.Handlers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 8/18/16.
 */
public class PackagesRecyclerAdapter extends RecyclerView.Adapter<PackagesRecyclerAdapter.PackagesRecyclerViewHolder> {

    private PackageItemClickListener mListener;

//    Setter for mListener


    public void setListener(final PackageItemClickListener listener) {
        this.mListener = listener;
    }

    Context mContext;
    ArrayList<CountryPackage> mCountryPackageArrayList;

    public PackagesRecyclerAdapter(Context context,ArrayList<CountryPackage> data) {
        mContext = context;
        mCountryPackageArrayList = data;
    }

    @Override
    public PackagesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_package_item,parent,false);
        return new PackagesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PackagesRecyclerViewHolder holder, int position) {

        String tit = mCountryPackageArrayList.get(position).getTitle();
        if(tit.contains("Travel and Tour Package"))
            tit = tit.replace("Travel and Tour Package", "");

        holder.title.setText(tit);
        holder.price.setText(mCountryPackageArrayList.get(position).getPrice());
        Picasso.with(mContext).load(mCountryPackageArrayList.get(position).getPimage()).resize(400,210).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mCountryPackageArrayList.size();
    }

    class PackagesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,price;
        ImageView mImageView;

        public PackagesRecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.packageNameTv);
            price = (TextView) itemView.findViewById(R.id.packagePriceTv);
            mImageView = (ImageView) itemView.findViewById(R.id.packageIV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onPackageItemClick(mCountryPackageArrayList.get(getAdapterPosition()),v);
            }
        }
    }

    interface PackageItemClickListener{
        void onPackageItemClick(CountryPackage aPackage,View view);
    }


}
