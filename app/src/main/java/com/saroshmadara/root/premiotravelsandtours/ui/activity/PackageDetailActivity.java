package com.saroshmadara.root.premiotravelsandtours.ui.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryPackage;
import com.squareup.picasso.Picasso;

public class PackageDetailActivity extends AppCompatActivity {

    private static final String TAG = PackageDetailActivity.class.getSimpleName();
    AppBarLayout mAppBarLayout;
    CountryPackage mPackage;
    TextView mFacilitiesTv,mStayTv,mHotelTv,mTermsTv;
    ImageView mOpenerTv,mOpener2;
    ExpandableRelativeLayout mExpandableLayout,mExpandableLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        mPackage = (CountryPackage) getIntent().getSerializableExtra("EXTRA_PKG");

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);


        Toolbar toolbar = (Toolbar)mAppBarLayout.findViewById(R.id.detail_toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.toolbarIv);
        Picasso.with(this).load(mPackage.getPimage()).into(imageView);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mPackage.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mFacilitiesTv = (TextView) findViewById(R.id.facilitiesTv);
        mHotelTv = (TextView) findViewById(R.id.hotelTv);
        mStayTv = (TextView) findViewById(R.id.stayTv);
        mTermsTv= (TextView) findViewById(R.id.termsAndCond);

        String temp = mPackage.getFacilities();
        if(temp.contains(","))
        temp = temp.replace(",","\n> ");

        temp = "> "+temp;
        mFacilitiesTv.setText(temp);

        mHotelTv.setText(mPackage.getHotel());
        mStayTv.setText(mPackage.getStay());

        mTermsTv.setText(getFormatedTerms(mPackage.getTerms()));


        mExpandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        mOpenerTv = (ImageView) findViewById(R.id.opener);
        mOpenerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mExpandableLayout.isExpanded())
                    mExpandableLayout.collapse();
                else
                    mExpandableLayout.expand();
            }
        });

        mExpandableLayout.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {

            }

            @Override
            public void onPreClose() {

            }

            @Override
            public void onOpened() {
//                Log.d(TAG,"layout opened");
                mOpenerTv.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand_less_black_24dp));
            }

            @Override
            public void onClosed() {
//                Log.d(TAG,"Layout closed");
                mOpenerTv.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            }
        });

        mExpandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        mOpener2 = (ImageView) findViewById(R.id.opener2);
        mOpener2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mExpandableLayout2.isExpanded())
                    mExpandableLayout2.collapse();
                else
                    mExpandableLayout2.expand();
            }
        });

        mExpandableLayout2.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {

            }

            @Override
            public void onPreClose() {

            }

            @Override
            public void onOpened() {
                mOpener2.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand_less_black_24dp));
            }

            @Override
            public void onClosed() {
                mOpener2.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            }
        });




    }

    private CharSequence getFormatedTerms(String terms) {
        String term[] = terms.split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for (int j=0; j<term.length;j++)
        stringBuilder.append("> "+term[j].trim()+"\n");

        return stringBuilder;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        switch (id){
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
        }
        return super.onOptionsItemSelected(item);
    }
}
