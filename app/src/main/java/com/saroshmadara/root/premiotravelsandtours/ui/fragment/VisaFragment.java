package com.saroshmadara.root.premiotravelsandtours.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.datasource.VisaDataSource;
import com.saroshmadara.root.premiotravelsandtours.model.CountryVisa;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisaFragment extends Fragment implements SearchView.OnQueryTextListener{

    private static final String TAG = VisaFragment.class.getSimpleName();

    private RecyclerView mVisaRecyclerView;
    private CountryVisaAdapter mAdapter;
    private ActionBar actionBar;
    private SearchView searchView;

    private VisaFragment mContext;

    public VisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_visa, container, false);
        mContext = this;
        setHasOptionsMenu(true);
        setUpActionBar();
        actionBar.setTitle("Visas");

        mVisaRecyclerView = (RecyclerView) v.findViewById(R.id.visaRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mVisaRecyclerView.setLayoutManager(manager);
        mAdapter = new CountryVisaAdapter(getActivity(), VisaDataSource.getData());
        mVisaRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new CountryVisaAdapter.OnVisaItemClickListener() {
            @Override
            public void onVisaItemClick(ArrayList<CountryVisa> data, View view, int position) {
//                Log.d(TAG,"visa item clicked");
                CountryVisa visa = VisaDataSource.getData().get(position);
                VisaDetailFragment fragment = new VisaDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("VISA_OBJ",visa);
                bundle.putInt("POSITION",position);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_scrolling,fragment)
                        .commit();


            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_country_visa,menu);

//        searchView.setOnQueryTextListener(this);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView = (SearchView) item.getActionView();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private void setUpActionBar() {
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpActionBar();
        actionBar.setTitle("Visas");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }
}
