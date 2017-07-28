/*
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.facebook.samples.litho.screen;

import android.os.Handler;
import android.support.v7.widget.OrientationHelper;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.litho.widget.RecyclerBinderUpdateCallback;
import com.facebook.samples.litho.ApiMainThreadCallback;
import com.facebook.samples.litho.R;
import com.facebook.samples.litho.playground.AutocompleteListItemComponent;
import com.facebook.samples.litho.playground.ContainerComponent;
import com.facebook.samples.litho.playground.TextInputComponent;
import com.facebook.samples.litho.playground.TextInputComponentSpec;
import com.facebook.samples.litho.provider.PlacesProvider;

import org.kisio.NavitiaSDK.NavitiaSDK;
import org.kisio.NavitiaSDK.invokers.ApiCallback;
import org.kisio.NavitiaSDK.invokers.ApiException;
import org.kisio.NavitiaSDK.models.PlaceResponse;
import org.kisio.NavitiaSDK.models.PlacesResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@LayoutSpec
public class AutocompleteComponentSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        final ComponentContext c,
        @Prop final NavitiaSDK navitia,
        @State Boolean stateLoading,
        @State List<PlaceResponse> stateResults) {

        final RecyclerBinder recyclerBinder = new RecyclerBinder(
            c,
            4.0f,
            new LinearLayoutInfo(c, OrientationHelper.VERTICAL, false)
        );
        final PlacesProvider placesProvider = new PlacesProvider(c, recyclerBinder, navitia);
        placesProvider.applyDatas(stateResults);

        final TextInputComponentSpec.OnQueryUpdateListener queryListener = new TextInputComponentSpec.OnQueryUpdateListener() {

            @Override
            public void onQueryUpdated(String query) {
                try {
                    placesProvider.getPlaces(query, new ApiMainThreadCallback<PlacesResponse>(c) {
                        @Override
                        public void onMainThreadFailure(ApiException var1, int var2, Map<String, List<String>> var3) {
                            AutocompleteComponent.updateLoadingState(c, false);
                        }

                        @Override
                        public void onMainThreadSuccess(PlacesResponse placesResponse, int var2, Map<String, List<String>> var3) {
                            AutocompleteComponent.updateResultsState(c, placesResponse.getPlaces());
                        }
                    });
                } catch (ApiException e) {
                    AutocompleteComponent.updateLoadingState(c, false);
                    e.printStackTrace();
                }
                AutocompleteComponent.updateLoadingState(c, true);
            }
        };

        return Column.create(c)
            .child(
                ContainerComponent.create(c)
                    .children(new Component<?>[]{
                        TextInputComponent.create(c)
                            .iconRes(R.drawable.ic_origin)
                            .iconColorRes(R.color.origin)
                            .queryListener(queryListener)
                            .build()
                    })
                    .withLayout()
                    .backgroundRes(R.color.tertiary)
            )
            .child(
                Recycler.create(c)
                    .binder(recyclerBinder)
                    .withLayout()
                    .flex(1)
            )
            .build();
    }

    @OnCreateInitialState
    static void createInitialState(
        ComponentContext c,
        StateValue<Boolean> stateLoading,
        StateValue<List<PlaceResponse>> stateResults) {

        stateLoading.set(false);
        stateResults.set(Collections.<PlaceResponse>emptyList());
    }

    @OnUpdateState
    static void updateLoadingState(
        StateValue<Boolean> stateLoading,
        @Param Boolean isLoading) {
        stateLoading.set(isLoading);
    }

    @OnUpdateState
    static void updateResultsState(
        StateValue<Boolean> stateLoading,
        StateValue<List<PlaceResponse>> stateResults,
        @Param List<PlaceResponse> results) {
        stateResults.set(results);
        stateLoading.set(false);
    }
}
