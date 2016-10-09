package com.saroshmadara.root.premiotravelsandtours.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryVisa;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisaDetailFragment extends Fragment {
    private static final String TAG = VisaDetailFragment.class.getSimpleName();
    TextView visaDescriptionTV,embassyReqTV,durationOfStayTV,visaFeeTV,countryName;
    ImageView countryIcon;
    Button termsBtn;
    View view;

    public VisaDetailFragment() {
        // Required empty public constructor
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        final ActionBar actionBar = ((AppCompatActivity)context).getSupportActionBar();
//
//        Log.d(TAG,"onattach");
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setTitle("Visa Adapter");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_visa_detail, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);


        termsBtn = (Button) view.findViewById(R.id.termsButton);
//        termsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getActivity().getSupportFragmentManager();
//                manager.beginTransaction()
//                        .add(R.id.content_scrolling,new TermsAndConditionFragment())
//                        .commit();
//
//                termsBtn.setVisibility(View.INVISIBLE);
//
//            }
//        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle i = getArguments();
        if (i.getInt("POSITION") != -1) {

            int position = i.getInt("POSITION", -1);
            CountryVisa visa = (CountryVisa) i.getSerializable("VISA_OBJ");


            visaDescriptionTV = (TextView) view.findViewById(R.id.visaDescription);
//            embassyReqTV = (TextView) findViewById(R.id.embassyRequirements);
//            durationOfStayTV = (TextView) findViewById(R.id.durationOfStay);
//            visaFeeTV = (TextView) findViewById(R.id.visaFee);
            countryName = (TextView) view.findViewById(R.id.countryName);
            countryIcon = (ImageView) view.findViewById(R.id.countryIcon);

            countryIcon.setImageResource(visa.getFlagId());
            countryName.setText(visa.getName());
            visaDescriptionTV.setText(visa.getDescription());


//            visaFeeTV.setText(countryVisa.getVisaFee());
//            durationOfStayTV.setText(countryVisa.getDurationOfStay());
//            embassyReqTV.setText(countryVisa.getEmbassyRequirements());
//            visaDescriptionTV.setText(countryVisa.getDescription());

        }
    }
}
