package com.nayam.itunesdiscover.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import butterknife.ButterKnife;

/**
 * @author May Ann Palencia on 08/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public abstract class BaseActivity extends DaggerBaseActivity {

    private ViewDataBinding activityMainBinding;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain binding object using the Data Binding library
        activityMainBinding = DataBindingUtil.setContentView(this, layoutRes());
        ButterKnife.bind(this);

    }

    public ViewDataBinding getActivityMainBinding() {
        return activityMainBinding;
    }

}
