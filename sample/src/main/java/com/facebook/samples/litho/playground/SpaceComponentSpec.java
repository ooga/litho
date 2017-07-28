package com.facebook.samples.litho.playground;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;

@LayoutSpec
public class SpaceComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {
        return Row.create(c)
            .widthDip(8)
            .heightDip(8)
            .build();
    }
}