package com.facebook.samples.litho.playground;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;

@LayoutSpec
public class FormComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop final Component<?>[] children) {

        ComponentLayout.ContainerBuilder containerBuilder = Column.create(c);
        containerBuilder.backgroundColor(Color.WHITE);

        for (Component<?> child : children) {
            containerBuilder.child(child);
        }

        return containerBuilder.build();
    }
}