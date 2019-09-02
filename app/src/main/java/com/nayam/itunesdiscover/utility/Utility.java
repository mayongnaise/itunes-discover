package com.nayam.itunesdiscover.utility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.nayam.itunesdiscover.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class as helper for application processes
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class Utility {

    /**
     *
     * @return This will return a formatted date object for the current datetime
     */
    public static String getCurrentDateTime(){

        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getDefault());

        return formatter.format(today);
    }

    /**
     *
     * @param dateValue The value of the date to format
     * @return This will return a newly formatted date based on this pattern: MMM dd, yyyy hh:mma
     */
    public static String formatDate(String dateValue){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        formatter.setLenient(false);

        Date date = null;
        try {
            date = formatter.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat convertedFormat = new SimpleDateFormat("MMM dd, yyyy hh:mma", Locale.getDefault());
        convertedFormat.setLenient(false);
        if (date != null) {
            return convertedFormat.format(date).replace("AM", "am").replace("PM", "pm");
        }

        return dateValue;
    }

    /**
     * Hides the SoftKeyboard
     *
     * @param activity This is the activity instance
     * @param editText This is the target view
     */
    public static void hideKeyboard(Activity activity, EditText editText){
        editText.clearFocus();
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null) {
            in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * Display an error text on an {@link EditText} widget
     *
     * @param editText This is the target view
     * @param error This is the text to display
     */
    public static void setEditTextError(EditText editText, String error){
        editText.setError(Html.fromHtml("<font color='red'>" + error + "</font>"));
    }

    /**
     * Get the media type item based on its position from the array pool of defined media type keys
     *
     * @param activity This is the activity instance
     * @param position This is the media type position
     *
     * @return This will return the media type string based on the selected media type position
     */
    public static String getMediaType(Activity activity, int position){
        return activity.getResources().getStringArray(R.array.category_selection_keys)[position];
    }

    /**
     * Get the value of network connectivity
     *
     * @param context This is the current context
     * @return This will return a boolean value to represent the network status
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    /**
     * Get the country code based on user's device Locale configuration
     *
     * @param context This is the current context
     * @return This will return the detected country code
     */
    public static String getDeviceCountryCode(Context context){
        return context.getResources().getConfiguration().locale.getCountry();
    }

}
