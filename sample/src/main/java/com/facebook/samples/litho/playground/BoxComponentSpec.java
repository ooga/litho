package com.facebook.samples.litho.playground;

import android.graphics.Color;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.samples.litho.R;

@LayoutSpec
public class BoxComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop final Component<?> content) {

        return Card.create(c)
            .elevationDip(2)
            .clippingColorRes(R.color.secondary)
            .shadowStartColor(Color.argb((int) (0.15 * 255), 0, 0,0))
            .cornerRadiusDip(5)
            .content(content)
            .buildWithLayout();
    }
}