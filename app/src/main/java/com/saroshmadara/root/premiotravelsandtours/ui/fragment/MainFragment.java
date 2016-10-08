package com.saroshmadara.root.premiotravelsandtours.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.Cloudinary;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryPackage;
import com.saroshmadara.root.premiotravelsandtours.rest.Handlers;
import com.saroshmadara.root.premiotravelsandtours.slider.ChildAnimationExample;
import com.saroshmadara.root.premiotravelsandtours.slider.TransformerAdapter;
import com.saroshmadara.root.premiotravelsandtours.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private static final String TAG = MainFragment.class.getSimpleName();
    //    UI References
    private TextView mSeeMoreBtn;
    private SliderLayout mDemoSlider;
    private RecyclerView mLatestDealRecyclerView;
    private ArrayList<CountryPackage> mCountryPackages = new ArrayList<>();


    public MainFragment() {
        // Required empty public constructor
    }
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_main, container, false);
        init(v);
        setUpSlider();
        fetchRecommendedPackages();




        return v;
    }

    private void setUpSlider() {

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Welcome To Premio Travels", "http://res.cloudinary.com/wear-n-care/image/upload/v1475964563/welcome_sdik7j.jpg");
        url_maps.put("Holiday Tours", "http://res.cloudinary.com/wear-n-care/image/upload/v1475964558/holidaytour_obthh2.jpg");
        url_maps.put("Cruise Packages", "http://res.cloudinary.com/wear-n-care/image/upload/v1475964569/cruisepkgs_hccmgp.jpg");
        url_maps.put("Tours Available", "http://res.cloudinary.com/wear-n-care/image/upload/v1475964554/touravail_w4fhiq.jpg");
        url_maps.put("Umrah Packages","http://res.cloudinary.com/wear-n-care/image/upload/v1475964566/umrah2016_wa555g.jpg");
        url_maps.put("Hotel Rooms Available","http://res.cloudinary.com/wear-n-care/image/upload/v1475964563/hotelrooms_v7abnx.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Welcome To Premio Travels",R.drawable.img1);
        file_maps.put("Holiday Tours",R.drawable.img2);
        file_maps.put("Cruise Packages",R.drawable.img3);
        file_maps.put("Tours Available", R.drawable.img4);
        file_maps.put("Umrah Packages", R.drawable.img4);
        file_maps.put("Hotel Rooms Available", R.drawable.img4);

        // loading images through uri

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout

            Picasso picasso = Picasso.with(getContext());
                    picasso.load(file_maps.get(name));


            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);


            // setting the transformer to
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(2500);
            mDemoSlider.addOnPageChangeListener(this);

        }

    }

    private void init(View v) {
        mSeeMoreBtn = (TextView) v.findViewById(R.id.see_more_btn);
        mLatestDealRecyclerView = (RecyclerView) v.findViewById(R.id.latestDealRecyclerView);
        mDemoSlider = (SliderLayout)v.findViewById(R.id.slider);
//        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);


//        listener on see_more_btn
        mSeeMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_scrolling,new PackagesListFragment()).commit();
            }
        });
    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
//        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    void fetchRecommendedPackages(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = Handlers.getInstance().getPackagesHandler();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    JSONArray packages = response.getJSONArray("Server_Responses");

                    for (int i = 0; i < 10; i++) {

                        CountryPackage countryPackage = new CountryPackage();
                        JSONObject packageObj = packages.getJSONObject(i);
                        countryPackage.setTitle(packageObj.getString("Name"));
                        countryPackage.setPrice(packageObj.getString("Price"));


                        // pick an local image for tour

                        countryPackage.setPimage(pickLocalImageForTour(packageObj.getString("Category").toLowerCase(),packageObj.getString("Name").toLowerCase()));

                        mCountryPackages.add(countryPackage);


                        countryPackage = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PackagesRecyclerAdapter adapter = new PackagesRecyclerAdapter(getContext(),mCountryPackages);
                mLatestDealRecyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private String pickLocalImageForTour(String category,String name) {

        switch (category){
            case "fareast":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689186/fareasttour_ep0xst.jpg";
            case "malaysia":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689166/malaysiatour_kcfpdi.jpg";
            case "turkey":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689188/turkeytour_adzc1l.jpg";
            case "dubai":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689159/dubaipackages_yozjak.jpg";
            case "srilanka":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689188/srilankatour_wfcaic.jpg";
//            case "cruise":
//                return "cruisetour"
            case "vietnam":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689158/vietnamtour_z2fm6z.jpg";
            case "thailand":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689163/thailandtour_codkyj.jpg";
            case "indonesia":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689187/indonesiatour_tadxsk.jpg";
            case "mauritius":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689182/mauritiustour_p7bgxm.jpg";
            case "switzerland":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689164/switzerlandtour_j3cfcu.jpg";
            case "paris":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689165/paristour_rxnbrx.png";
            case "europe":
                return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689162/europetour_jealxw.jpg";
            default: {
                return pickImageForMultipleCountryTour(name);
            }

        }

    }

    private String pickImageForMultipleCountryTour(String name) {

        if(name.contains("three"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689188/countries_package_cqukva.jpg";
//        else if(name.contains("four"))
//            return "";
        else if (name.contains("five"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689186/fivecountriespkgs_g0t9ls.jpg";
        else if(name.contains("six"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689178/multiplecitytour_yqmbom.png";
        else if(name.contains("honeymoon"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689185/honeymoontour_h8hv2g.jpg";
        else if(name.contains("holiday"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689181/multiplecitietour_id19hy.png";
        else if(name.contains("gulf"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689183/arabiangulfcruisedubai_hmae28.jpg";
        else if(name.contains("and"))
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475762741/confessions-header-2_uayyt8.jpg";
        else
            return "http://res.cloudinary.com/wear-n-care/image/upload/v1475689183/imageplaceholdertour_t4xqfo.png";
    }


}

