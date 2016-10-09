package com.saroshmadara.root.premiotravelsandtours.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.saroshmadara.root.premiotravelsandtours.Helper.Methods;
import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryPackage;
import com.squareup.picasso.Picasso;

public class PackageDetailActivity extends AppCompatActivity {

    private static final String TAG = PackageDetailActivity.class.getSimpleName();
    AppBarLayout mAppBarLayout;
    CollapsingToolbarLayout mToolbarLayout;
    CountryPackage mPackage;
    TextView mFacilitiesTv, mdestinationTv, mdepartureTv,mTermsTv,mVisaIncludedTv,mTicketIncludedTv,mMinAllowedTv;
    ImageView mOpenerTv,mOpener2;
    ExpandableRelativeLayout mExpandableLayout,mExpandableLayout2;
    LinearLayout mCallLinLayout;

    PackageDetailActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        mPackage = (CountryPackage) getIntent().getSerializableExtra("EXTRA_PKG");
        mContext = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods.contactUs(mContext);
            }
        });

        final Toolbar toolbar = (Toolbar)findViewById(R.id.detail_toolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    mToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.black));
                } else if (verticalOffset == 0) {
                    // Expanded
                    mToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
                } else {
                    // Somewhere in between
                }
            }
        });

        mCallLinLayout= (LinearLayout) findViewById(R.id.callLinLayout);
        mCallLinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods.contactUs(mContext);
            }
        });


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
        mdepartureTv = (TextView) findViewById(R.id.departureTv);
        mdestinationTv = (TextView) findViewById(R.id.destinationTv);
        mTermsTv= (TextView) findViewById(R.id.termsAndCond);
        mVisaIncludedTv = (TextView) findViewById(R.id.visaIncludedTv);
        mTicketIncludedTv = (TextView) findViewById(R.id.ticketsPkgTv);
        mMinAllowedTv = (TextView) findViewById(R.id.minAllowTv);


        mVisaIncludedTv.setText(makeUpperCase(mPackage.getVisa()));
        mTicketIncludedTv.setText(makeUpperCase(mPackage.getTicket()));
        mMinAllowedTv.setText(makeUpperCase(mPackage.getMinallow())+" minimum allowed");


        String temp = mPackage.getFacilities();
        if(temp.contains(","))
        temp = temp.replace(',','\n');

//        temp = "> "+temp;
        mFacilitiesTv.setText(temp);

        mdepartureTv.setText(mPackage.getDcontcity());

        int countriesLen = mPackage.getDestcont().split(",").length;
        int cityLen = mPackage.getDestcity().split(",").length;
        if (countriesLen == cityLen) {
//            Log.d(TAG,"len equal");
            String[] cities = mPackage.getDestcity().split(",");
            String[] countries = mPackage.getDestcont().split(",");
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<cityLen;i++)
                if(i == cityLen-1) builder.append(cities[i]+", "+countries[i]);
                else builder.append(cities[i]+", "+countries[i]+"\n");

            mdestinationTv.setText(builder);
        }

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

    private String  makeUpperCase(String visa) {
        if(visa.length()>0){

            char firstChar = visa.charAt(0);
            char newChar = String.valueOf( firstChar).toUpperCase().charAt(0);

            return visa.replace(visa.charAt(0),newChar);
        }
        return "Not Included";
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
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
