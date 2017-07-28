package com.facebook.samples.litho.playground;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.widget.ImageView;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.reference.DrawableReference;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.samples.litho.R;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class EmbeddedTypeComponentSpec {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop final String name) {

        int iconReference = R.drawable.ic_poi;
        switch (name) {
            case "Home":
                iconReference = R.drawable.ic_home;
                break;
            case "Work":
                iconReference = R.drawable.ic_work;
                break;
            case "address":
                iconReference = R.drawable.ic_address;
                break;
            case "administrative_region":
                iconReference = R.drawable.ic_administrative_region;
                break;
            case "poi":
                iconReference = R.drawable.ic_poi;
                break;
            case "stop_point":
            case "stop_area":
                iconReference = R.drawable.ic_stop;
                break;
        }

        return Image.create(c)
            .key("icon")
//            .scaleType(ImageView.ScaleType.FIT_CENTER)
            .drawable(c.getDrawable(iconReference))
            .withLayout()
            .widthDip(22)
            .heightDip(22)
            .build();
    }
}