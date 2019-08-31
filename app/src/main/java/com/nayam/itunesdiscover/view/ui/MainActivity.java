package com.nayam.itunesdiscover.view.ui;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.databinding.ActivityMainBinding;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.utility.Utility;
import com.nayam.itunesdiscover.view.adapter.TrackRecyclerViewAdapter;
import com.nayam.itunesdiscover.viewmodel.SearchViewModel;
import com.nayam.itunesdiscover.viewmodel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    ArrayList<Track> trackArrayList = new ArrayList<>();
    TrackRecyclerViewAdapter trackRecyclerViewAdapter;

    @BindView(R.id.recyclerViewTrack)
    RecyclerView rvTrackList;

    @BindView(R.id.segmentedCategories)
    SegmentedControl smCategories;

    @BindView(R.id.editTextTerm)
    EditText etTerm;

    TrackViewModel trackViewModel;
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.init(MainActivity.this);

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
        trackViewModel.searchTrack(searchViewModel, MainActivity.this);

        setSearchTerm();

        setCategories();

        setLastSearchDate();

        loadTrackList();

        setupRecyclerView();

    }

    private void loadTrackList(){
        trackViewModel.getTrackRepository().observe(this, trackResponse -> {
            List<Track> tracks = trackResponse.getResults();
            trackArrayList.addAll(tracks);
            trackRecyclerViewAdapter.notifyDataSetChanged();
            searchViewModel.setLastSearchDate(this, Utility.getCurrentDateTime());
        });

    }

    private void performSearch(){
        String term = etTerm.getText().toString();
        if(TextUtils.isEmpty(term)) {
            Utility.setEditTextError(etTerm, getString(R.string.required));
            return;
        }

        searchViewModel.setTerm(MainActivity.this, term);

        trackArrayList.clear();
        trackViewModel.searchTrack(searchViewModel,MainActivity.this);

        loadTrackList();
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

    private void setSearchTerm(){
        activityMainBinding.layoutHeader.setSearchTerm(searchViewModel.getTerm(MainActivity.this));
    }

    private void setCategories(){
        smCategories.setSelectedSegment(searchViewModel.getMediaTypePosition(MainActivity.this));
    }

    private void setLastSearchDate(){
        activityMainBinding.layoutHeader.setLastSearch(Utility.formatDate(searchViewModel.getLastSearchDate(this)));
    }

    @OnClick(R.id.imageFilter)
    public void categoryVisibility(){
        smCategories.setVisibility(smCategories.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        smCategories.addOnSegmentClickListener(segmentViewHolder -> {
            int mediaTypePosition = segmentViewHolder.getAbsolutePosition();
            searchViewModel.setMediaTypePosition(MainActivity.this, mediaTypePosition);
            String mediaType = getResources().getStringArray(R.array.category_selection_keys)[mediaTypePosition];
            searchViewModel.setMediaType(MainActivity.this, mediaType);

            performSearch();

        });
    }

    @OnEditorAction(R.id.editTextTerm)
    public boolean onEditorAction(int actionId){
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            Utility.hideKeyboard(MainActivity.this, etTerm);
            performSearch();
            return true;
        }
        return false;
    }

}
