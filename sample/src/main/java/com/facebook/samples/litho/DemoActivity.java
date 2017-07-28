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

import com.facebook.litho.LithoView;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.HomeTheme);

        Demos.initialize(this);

        final String demoName = (String) getIntent().getSerializableExtra("demoName");


        LithoView lithoView = LithoView.create(this, Demos.getComponent(demoName));
        lithoView.setBackgroundColor(
            this.getResources().getColor(R.color.secondary)
        );
        setContentView(lithoView);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setLogo(getDrawable(R.drawable.logo));
        actionBar.setElevation(0);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME);

    }
}
