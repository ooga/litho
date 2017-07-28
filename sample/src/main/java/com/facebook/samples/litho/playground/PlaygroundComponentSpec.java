/*
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.facebook.samples.litho.playground;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.SolidColor;
import com.facebook.samples.litho.R;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class PlaygroundComponentSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Column.create(c)
            .paddingDip(YogaEdge.ALL, 8)
            .child(
                Row.create(c)
                    .child(
                        BigButtonComponent.create(c)
                            .text("Schedule")
                            .iconRes(R.drawable.ic_clock)
                    )
                    .child(
                        SpaceComponent.create(c))
                    .child(
                        BigButtonComponent.create(c)
                            .text("Vélib")
                            .iconRes(R.drawable.ic_bss)
                    )
                    .build()
            )
            .child(SpaceComponent.create(c))
            .child(
                BoxComponent.create(c)
                    .content(
                        FormComponent.create(c)
                            .children(new Component<?>[]{
                                AutocompleteInputComponent.create(c)
                                    .iconRes(R.drawable.ic_origin)
                                    .iconColorRes(R.color.origin)
                                    .title("Origin")
                                    .placeholder("Fill your origin")
                                    .build(),
                                SeparatorComponent.create(c)
                                    .build(),
                                AutocompleteInputComponent.create(c)
                                    .iconRes(R.drawable.ic_destination)
                                    .iconColorRes(R.color.destination)
                                    .title("Destination")
                                    .placeholder("Fill your destination")
                                    .build()
                            })
                            .build()
                    )
            )
            .child(SpaceComponent.create(c))
            .child(
                BoxComponent.create(c)
                    .content(
                        FormComponent.create(c)
                            .children(new Component<?>[]{
                                AutocompleteInputComponent.create(c)
                                    .iconRes(R.drawable.ic_home)
                                    .iconColorRes(R.color.tertiary)
                                    .title("Back to home")
                                    .placeholder("12 Rue Hector Malot (Paris)")
                                    .build(),
                                SeparatorComponent.create(c)
                                    .build(),
                                AutocompleteInputComponent.create(c)
                                    .iconRes(R.drawable.ic_work)
                                    .title("Go to Work")
                                    .placeholder("Fill in your work address")
                                    .build()
                            })
                            .build()
                    )
            )
            .build();
    }
}
