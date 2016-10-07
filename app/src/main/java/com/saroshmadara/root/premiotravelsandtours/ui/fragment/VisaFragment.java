package com.saroshmadara.root.premiotravelsandtours.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.datasource.VisaDataSource;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisaFragment extends Fragment implements SearchView.OnQueryTextListener{

    private RecyclerView mVisaRecyclerView;
    private CountryVisaAdapter mAdapter;
    private ActionBar actionBar;
    private SearchView searchView;

    public VisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_visa, container, false);
        setHasOptionsMenu(true);
        setUpActionBar();
        actionBar.setTitle("Visas");

        mVisaRecyclerView = (RecyclerView) v.findViewById(R.id.visaRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mVisaRecyclerView.setLayoutManager(manager);
        mAdapter = new CountryVisaAdapter(getActivity(), VisaDataSource.getData());
        mVisaRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_country_visa,menu);

//        searchView.setOnQueryTextListener(this);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
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
}
