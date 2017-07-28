package com.facebook.samples.litho.provider;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.samples.litho.playground.AutocompleteListItemComponent;

import org.kisio.NavitiaSDK.NavitiaSDK;
import org.kisio.NavitiaSDK.invokers.ApiCallback;
import org.kisio.NavitiaSDK.invokers.ApiException;
import org.kisio.NavitiaSDK.models.PlaceResponse;
import org.kisio.NavitiaSDK.models.PlacesResponse;

import java.util.List;

public class PlacesProvider {
    private ComponentContext c;
    private RecyclerBinder binder;
    private NavitiaSDK navitia;

    public PlacesProvider(ComponentContext c, RecyclerBinder binder, NavitiaSDK navitia) {
        this.c = c;
        this.binder = binder;
        this.navitia = navitia;
    }

    public void getPlaces(String query, final ApiCallback<PlacesResponse> apiCallback) throws ApiException {
        navitia.getPlacesApi().getPlacesRequestBuilder().withQ(query).execute(apiCallback);
    }

    public void applyDatas(List<PlaceResponse> places) {
        binder.removeRangeAt(0, binder.getItemCount());

        for (PlaceResponse place : places) {
            ComponentInfo.Builder placeComponentInfoBuilder = ComponentInfo.create();
            placeComponentInfoBuilder.component(
                AutocompleteListItemComponent.create(c)
                    .place(place)
                    .build()
            );
            binder.insertItemAt(
                binder.getItemCount(),
                placeComponentInfoBuilder.build());
        }
    }
}
