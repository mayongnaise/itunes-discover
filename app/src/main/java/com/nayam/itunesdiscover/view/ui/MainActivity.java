package com.nayam.itunesdiscover.view.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hbb20.CountryCodePicker;
import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.data.local.SharedPreferenceHelper;
import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;
import com.nayam.itunesdiscover.databinding.ActivityMainBinding;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.model.TrackResponse;
import com.nayam.itunesdiscover.utility.Constants;
import com.nayam.itunesdiscover.utility.Utility;
import com.nayam.itunesdiscover.view.adapter.TrackRecyclerViewAdapter;
import com.nayam.itunesdiscover.viewmodel.MainViewModel;
import com.nayam.itunesdiscover.viewmodel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @BindView(R.id.recyclerViewTrack)
    RecyclerView rvTrackList;

    @BindView(R.id.pickerCountry)
    CountryCodePicker pkCountry;

    @BindView(R.id.segmentedCategories)
    SegmentedControl smCategories;

    @BindView(R.id.editTextTerm)
    EditText etTerm;

    ArrayList<Track> trackArrayList = new ArrayList<>();
    TrackRecyclerViewAdapter trackRecyclerViewAdapter;

    TrackViewModel trackViewModel;
    MainViewModel mainViewModel;

    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    @Override
    public void onResume() {
        sharedPreferenceHelper.setLastActivity(getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        setViewModel();

        setUI();

        callSearch();

    }

    public void setViewModel(){

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        activityMainBinding.layoutHeader.setViewModel(mainViewModel);
        activityMainBinding.layoutContentMain.setViewModel(mainViewModel);

    }

    public void setUI(){

        mainViewModel.setSearchTerm(sharedPreferenceHelper.getTerm());

        mainViewModel.setCountry(sharedPreferenceHelper.getCountry());
        pkCountry.setCountryForNameCode(sharedPreferenceHelper.getCountry());
        pkCountry.setCountryPreference(String.format("%s,%s", Utility.getDeviceCountryCode(MainActivity.this), Constants.DEFAULT_COUNTRY));

        mainViewModel.setLastSearch(Utility.formatDate(sharedPreferenceHelper.getLastSearchDate()));

        activityMainBinding.layoutHeader.segmentedCategories.setSelectedSegment(sharedPreferenceHelper.getMediaTypePosition());

        activityMainBinding.layoutContentMain.swipeRefreshLayout.setOnRefreshListener(this::attemptSearch);

        setupCountryChangeListener();

        setupCategoryFilterListener();

        setupRecyclerView();
    }

    private void setupCountryChangeListener(){
        mainViewModel.getCountry().observe(this, country -> {
            sharedPreferenceHelper.setCountry(country);
            callSearch();
        });

    }

    private void setupCategoryFilterListener(){

        smCategories.addOnSegmentClickListener(segmentViewHolder -> {

            sharedPreferenceHelper.setMediaTypePosition(segmentViewHolder.getAbsolutePosition());

            attemptSearch();

        });

    }

    private void setupRecyclerView() {
        if (trackRecyclerViewAdapter == null) {
            trackRecyclerViewAdapter = new TrackRecyclerViewAdapter(MainActivity.this, trackArrayList);
            rvTrackList.setLayoutManager(new LinearLayoutManager(this));
            rvTrackList.setAdapter(trackRecyclerViewAdapter);
            rvTrackList.setItemAnimator(new DefaultItemAnimator());
            rvTrackList.setNestedScrollingEnabled(true);
        } else {
            trackRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @OnEditorAction(R.id.editTextTerm)
    public boolean onEditorAction(int actionId){
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            Utility.hideKeyboard(MainActivity.this, etTerm);
            attemptSearch();
            return true;
        }
        return false;
    }

    private void attemptSearch(){
        String term = etTerm.getText().toString();
        if(TextUtils.isEmpty(term)) {
            Utility.setEditTextError(etTerm, getString(R.string.required));
            return;
        }

        sharedPreferenceHelper.setTerm(term);

        sharedPreferenceHelper.setLastSearchDate(Utility.getCurrentDateTime());

        mainViewModel.setLastSearch(Utility.formatDate(sharedPreferenceHelper.getLastSearchDate()));

        callSearch();

    }

    public void callSearch(){
        trackArrayList.clear();
        trackViewModel.searchTrack(sharedPreferenceHelper.getTerm(), sharedPreferenceHelper.getCountry(), Utility.getMediaType(MainActivity.this, sharedPreferenceHelper.getMediaTypePosition()));
        loadTrackList();
    }

    private void loadTrackList(){
        mainViewModel.setRefreshing(true);
        trackViewModel.getTrackRepository().observe(this, trackResponseResult -> {
            if(trackResponseResult.getResponseBody().isSuccessful()){
                List<Track> tracks = ((TrackResponse) trackResponseResult.getTrackResponse()).getResults();
                trackArrayList.addAll(tracks);
                trackRecyclerViewAdapter.notifyDataSetChanged();
            }
            else{
                if(!Utility.isNetworkAvailable(MainActivity.this))
                    Toast.makeText(MainActivity.this, getString(R.string.no_network_available), Toast.LENGTH_SHORT).show();
            }
            mainViewModel.setRefreshing(false);
        });

    }

}
