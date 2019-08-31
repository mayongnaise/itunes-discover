package com.nayam.itunesdiscover.utility;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.view.ui.MainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class Utility {

    public static String getCurrentDateTime(){

        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getDefault());

        return formatter.format(today);
    }

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

    public static void hideKeyboard(Activity activity, EditText editText){
        editText.clearFocus();
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null) {
            in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static void setEditTextError(EditText editText, String error){
        editText.setError(Html.fromHtml("<font color='red'>" + error + "</font>"));
    }


}
