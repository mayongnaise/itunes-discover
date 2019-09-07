package com.nayam.itunesdiscover.view.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hbb20.CountryCodePicker;
import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.data.local.SharedPreferenceHelper;
import com.nayam.itunesdiscover.databinding.ActivityMainBinding;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.model.TrackResponse;
import com.nayam.itunesdiscover.utility.Constants;
import com.nayam.itunesdiscover.utility.Utility;
import com.nayam.itunesdiscover.view.adapter.TrackRecyclerViewAdapter;
import com.nayam.itunesdiscover.view.base.BaseActivity;
import com.nayam.itunesdiscover.viewmodel.MainViewModel;
import com.nayam.itunesdiscover.viewmodel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;

/**
 *
 * Activity to display the list of items from iTunes Search API
 *
 * This activity is used to display the resulting data from a search query using iTunes Search API.
 * Search terms and filters can be modified in this activity to make the search query dynamic.
 * The resulting data is displayed using {@link RecyclerView} widget with
 * {@link androidx.swiperefreshlayout.widget.SwipeRefreshLayout} to refresh the list.
 *
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class MainActivity extends BaseActivity {

    /**
     * Data binding for activity
     */
    ActivityMainBinding activityMainBinding;

    /**
     * Activity UI components
     */
    @BindView(R.id.recyclerViewTrack)
    RecyclerView rvTrackList;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout srlLoading;

    @BindView(R.id.pickerCountry)
    CountryCodePicker pkCountry;

    @BindView(R.id.segmentedCategories)
    SegmentedControl smCategories;

    @BindView(R.id.editTextTerm)
    EditText etTerm;

    String countryCode = Constants.DEFAULT_COUNTRY_CODE;

    int mediaTypePosition = Constants.DEFAULT_MEDIA_POSITION;

    String term = Constants.DEFAULT_TERM;

    /**
     * {@link ArrayList} to hold the resulting track items from API call
     */
    ArrayList<Track> trackArrayList = new ArrayList<>();

    /**
     * {@link RecyclerView} adapter to bind the resulting data to the activity view
     */
    TrackRecyclerViewAdapter trackRecyclerViewAdapter;

    /**
     * {@link androidx.lifecycle.ViewModel} to hold {@link Track} data
     */
    TrackViewModel trackViewModel;

    /**
     * {@link androidx.lifecycle.ViewModel} to hold {@link MainActivity} configuration data
     */
    MainViewModel mainViewModel;

    /**
     * Helper class to manage {@link android.content.SharedPreferences}
     */
    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    /**
     * Saving the {@link MainActivity} as the last visited activity
     */
    @Override
    public void onResume() {
        sharedPreferenceHelper.setLastActivity(getClass().getName());
        super.onResume();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Obtain binding object using the Data Binding library
        activityMainBinding = (ActivityMainBinding) getActivityMainBinding();

        setViewModel();

        setUI();

        callSearch();

    }

    /**
     * Method to get the view models to handle data for this UI
     */
    public void setViewModel(){

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        activityMainBinding.setViewModel(mainViewModel);

    }

    /**
     * Method to set default values of the activity UI
     */
    public void setUI(){

        // Set term value and bind it to the search EditText widget
        mainViewModel.setSearchTerm(sharedPreferenceHelper.getTerm());

        // Set last search date and bind it to the date TextView widget
        mainViewModel.setLastSearch(Utility.formatDate(sharedPreferenceHelper.getLastSearchDate()));

        // Set selected country and preference
        pkCountry.setCountryForNameCode(sharedPreferenceHelper.getCountryCode());
        pkCountry.setCountryPreference(String.format("%s,%s", Utility.getDeviceCountryCode(MainActivity.this), Constants.DEFAULT_COUNTRY_CODE));

        // Set media type and bind it to the SegmentedControl widget
        smCategories.setSelectedSegment(sharedPreferenceHelper.getMediaTypePosition());

        // Calling attemptSearch method when user swipes the track list to attempt calling Search API
        srlLoading.setOnRefreshListener(this::attemptSearch);

        observeCountryCodeValue();

        setupCategoryFilterListener();

        setupRecyclerView();
    }

    /**
     * Calling method to observe any changes on the country code value
     */
    private void observeCountryCodeValue(){
        mainViewModel.getCountry().observe(this, countryCode -> {
            this.countryCode = countryCode;
            attemptSearch();
        });

    }

    /**
     * Listening to filter category changes
     */
    private void setupCategoryFilterListener(){

        smCategories.addOnSegmentClickListener(segmentViewHolder -> {

            mediaTypePosition = segmentViewHolder.getAbsolutePosition();
            attemptSearch();

        });

    }

    /**
     * Set up RecyclerView adapter and layout manager
     */
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

    /**
     * Binded action for the search {@link EditText} widget
     * Call attemptSearch method after user hits the search button in the SoftKeyboard
     *
     * @param actionId The actionId returned by the listener. Only proceeds if value is EditorInfo.IME_ACTION_SEARCH
     * @return The result if the search action is handled
     */
    @OnEditorAction(R.id.editTextTerm)
    public boolean onEditorAction(int actionId){
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            attemptSearch();
            return true;
        }
        return false;
    }

    /**
     * Evaluate if all requirements are completed for a valid Search API call
     * The parameter key 'term' is required  by the iTunes Search api so check first if value is not empty before proceeding
     */
    private void attemptSearch(){
        Utility.hideKeyboard(MainActivity.this, etTerm);
        term = etTerm.getText().toString();

        if(TextUtils.isEmpty(term)) {
            Utility.setEditTextError(etTerm, getString(R.string.required));
            return;
        }

        saveData();

        mainViewModel.setLastSearch(Utility.formatDate(sharedPreferenceHelper.getLastSearchDate()));

        callSearch();

    }

    /**
     * Method to save the data on {@link android.content.SharedPreferences}
     */
    public void saveData(){

        // Save new value of country code locally
        sharedPreferenceHelper.setCountryCode(countryCode);

        // Save new value of search term locally
        sharedPreferenceHelper.setTerm(term);

        // Save new value of media type position locally
        sharedPreferenceHelper.setMediaTypePosition(mediaTypePosition);

        // Save new value of last search date locally
        sharedPreferenceHelper.setLastSearchDate(Utility.getCurrentDateTime());
    }

    /**
     * Method to call the searchTrack function from network repository. This function will call the iTunes Search API
     */
    public void callSearch(){
        trackArrayList.clear();
        trackViewModel.searchTrack(sharedPreferenceHelper.getTerm(), sharedPreferenceHelper.getCountryCode(), Utility.getMediaType(MainActivity.this, sharedPreferenceHelper.getMediaTypePosition()));
        observeTrackDataResponse();
    }

    /**
     * Observe API call response and update {@link RecyclerView} list for data changes
     */
    private void observeTrackDataResponse(){
        mainViewModel.setRefreshing(true);
        trackViewModel.getTrackRepository().observe(this, trackResponseResult -> {
            if(trackResponseResult.getResponseBody().isSuccessful()){
                List<Track> tracks = ((TrackResponse) trackResponseResult.getResponse()).getResults();
                trackArrayList.addAll(tracks);
                trackRecyclerViewAdapter.notifyDataSetChanged();

                mainViewModel.setIsResultEmpty(tracks.size() <= 0);
            }
            else{
                // Show a message feedback to user when api response is not successful due to network error
                if(!Utility.isNetworkAvailable(MainActivity.this))
                    Toast.makeText(MainActivity.this, getString(R.string.no_network_available), Toast.LENGTH_SHORT).show();
            }
            mainViewModel.setRefreshing(false);
        });

    }

}
