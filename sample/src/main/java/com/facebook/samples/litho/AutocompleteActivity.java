/*
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.facebook.samples.litho;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.samples.litho.provider.PlacesProvider;
import com.facebook.samples.litho.screen.AutocompleteComponent;

import org.kisio.NavitiaSDK.NavitiaConfiguration;
import org.kisio.NavitiaSDK.NavitiaSDK;

public class AutocompleteActivity extends AppCompatActivity {
    private NavitiaSDK navitia;

    public AutocompleteActivity() {
        NavitiaConfiguration config = new NavitiaConfiguration("0de19ce5-e0eb-4524-a074-bda3c6894c19");
        navitia = new NavitiaSDK(config);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ComponentContext componentContext = new ComponentContext(this);

        setContentView(
            LithoView.create(
                this,
                AutocompleteComponent.create(componentContext)
                    .navitia(navitia)
                    .build()
            )
        );

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Where are you coming from ?");
        actionBar.setElevation(0);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
