package com.facebook.samples.litho.playground;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;

@LayoutSpec
public class SeparatorComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Column.create(c)
            .heightPx(1)
            .backgroundColor(Color.parseColor("#F0F0F0"))
            .build();
    }
}