package com.saroshmadara.root.premiotravelsandtours.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryPackage;
import com.saroshmadara.root.premiotravelsandtours.rest.Handlers;
import com.saroshmadara.root.premiotravelsandtours.ui.activity.PackageDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackagesListFragment extends Fragment {


    private static final String TAG = PackagesListFragment.class.getSimpleName();
    RecyclerView packageRecyclerView;
    private PackagesRecyclerAdapter mAdapter;
    private String mPackagesStream;
    private ProgressDialog mDialog;
    private ActionBar actionBar;
    private ArrayList<CountryPackage> mCountryPackages = new ArrayList<>();

    public PackagesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_packages_list, container, false);
        setUpActionBar();
        actionBar.setTitle("Packages");
        init(view);


        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        packageRecyclerView.setLayoutManager(manager);
        fetchRecommendedPackages();




//        new FetchPackagesTask().execute();
//        mAdapter = new PackagesRecyclerAdapter(getActivity(),null,R.layout.country_packages_item);


        return view;
    }

    private void init(View view) {
        packageRecyclerView = (RecyclerView) view.findViewById(R.id.packagesRecyclerView);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_packages_list,menu);
        mMenuInflater = inflater;
    }

    private MenuInflater mMenuInflater;
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        mMenuInflater.inflate(R.menu.menu_packages_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.category_show:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                packageRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            case R.id.list_show:
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                packageRecyclerView.setLayoutManager(manager);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpActionBar();
        actionBar.setTitle("Packages");
    }


//    private class FetchPackagesTask extends AsyncTask<Void,Void,String>{
//        String packagesUrl;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            packagesUrl = Handlers.getInstance().getPackagesHandler();
//            setupProgressDialog();
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//
//            try {
//                URL url = new URL(packagesUrl);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder stringBuilder = new StringBuilder();
//                while ((mPackagesStream = bufferedReader.readLine()) != null){
//                    stringBuilder.append(mPackagesStream);
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return stringBuilder.toString().trim();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            mDialog.dismiss();
//            Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
//
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//
////                JSONObject serverResponse = jsonObject.getJSONObject("Server_Responses");
//                JSONArray packages = jsonObject.getJSONArray("Server_Responses");
//
//
//
//                Toast.makeText(getActivity(),"packages size:: " + packages.length(),Toast.LENGTH_SHORT).show();
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    private void setUpActionBar() {
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
    }
    private void setupProgressDialog() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setCancelable(false);
        mDialog.setIndeterminate(true);
        mDialog.setIndeterminateDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.custom_dialog));
        mDialog.setMessage("Loading Packages....");
        mDialog.show();
    }


    void fetchRecommendedPackages(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = Handlers.getInstance().getPackagesHandler();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray packages = response.getJSONArray("Server_Responses");

                    for (int i = 0; i <packages.length(); i++) {

                        CountryPackage countryPackage = new CountryPackage();
                        JSONObject packageObj = packages.getJSONObject(i);
                        countryPackage.setTitle(packageObj.getString("Name"));
                        countryPackage.setPrice(packageObj.getString("Price"));
                        countryPackage.setFacilities(packageObj.getString("Facilities"));
                        countryPackage.setStay(packageObj.getString("Stay"));
                        countryPackage.setHotel(packageObj.getString("Hotel"));
                        countryPackage.setTerms(packageObj.getString("Terms and Conditions"));

//                        Log.d("testp",packageObj.getString("Stay")+packageObj.getString("Hotel")+packageObj.getString("Facilities"));

                        String imageURL = "http://www.premiotravels.com/im/package/"+packageObj.getString("Category")+"/"+packageObj.getString("Pimage");
                        countryPackage.setPimage(imageURL);
                        mCountryPackages.add(countryPackage);
                        countryPackage = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter = new PackagesRecyclerAdapter(getContext(),mCountryPackages);
                packageRecyclerView.setAdapter(mAdapter);
                mAdapter.setListener(new PackagesRecyclerAdapter.PackageItemClickListener() {
                    @Override
                    public void onPackageItemClick(CountryPackage aPackage, View view) {
                        Log.d(TAG,"Package Name: "+aPackage.getTitle());
                        Intent intent = new Intent(getContext(), PackageDetailActivity.class);
                        intent.putExtra("EXTRA_PKG",aPackage);
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.packageIV),"transitionTourPkgIV");
                        startActivity(intent,activityOptionsCompat.toBundle());
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

}
