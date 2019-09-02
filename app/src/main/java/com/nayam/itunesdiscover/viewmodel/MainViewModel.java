package com.nayam.itunesdiscover.viewmodel;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hbb20.CountryCodePicker;

/**
 * ViewModel of {@link com.nayam.itunesdiscover.view.ui.MainActivity} to manage UI data
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class MainViewModel extends ViewModel {

    /**
     * Observable value whether to show refreshing state of {@link SwipeRefreshLayout}
     */
    private ObservableField<Boolean> isRefreshing = new ObservableField<>();

    /**
     * Observable value whether to show category filter
     */
    private ObservableField<Boolean> isCategoryFilterVisible = new ObservableField<>();

    /**
     * Observable value of last search date which is binded on a {@link android.widget.TextView} widget
     */
    private ObservableField<String> lastSearch = new ObservableField<>();

    /**
     * Observable value of term which is binded on a {@link android.widget.EditText} widget
     */
    private ObservableField<String> searchTerm = new ObservableField<>();

    /**
     * Observable value of country code which is binded on {@link CountryCodePicker} widget
     */
    private MutableLiveData<String> country = new MutableLiveData<>();

    public MainViewModel(){

    }

    @BindingAdapter({"refreshVisibility"})
    public static void setAnimatedVisibility(SwipeRefreshLayout view, boolean isRefreshing) {
        view.setRefreshing(isRefreshing);
    }

    @BindingAdapter({"animatedVisibility"})
    public static void setAnimatedVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("countryValue")
    public static void setCountryValue(final CountryCodePicker picker, MutableLiveData country) {
        picker.setOnCountryChangeListener(() -> country.postValue(picker.getSelectedCountryNameCode()));
    }

    public void onCategoryFilterClick(boolean isVisible){
        isCategoryFilterVisible.set(!isVisible);
    }

    public ObservableField<Boolean> isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean progressVisible) {
        this.isRefreshing.set(progressVisible);
    }

    public ObservableField<Boolean> getIsCategoryFilterVisible() {
        return isCategoryFilterVisible;
    }

    public ObservableField<String> getLastSearch() {
        return lastSearch;
    }

    public void setLastSearch(String lastSearch) {
        this.lastSearch.set(lastSearch);
    }

    public ObservableField<String> getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm.set(searchTerm);
    }

    public MutableLiveData<String> getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country.postValue(country);
    }

}
