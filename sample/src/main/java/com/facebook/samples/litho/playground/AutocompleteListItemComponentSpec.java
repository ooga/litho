/*
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.facebook.samples.litho.playground;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;

import org.kisio.NavitiaSDK.models.PlaceResponse;

import static com.facebook.yoga.YogaEdge.ALL;

@LayoutSpec
public class AutocompleteListItemComponentSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop final PlaceResponse place) {

        return Row.create(c)
            .paddingDip(ALL, 16)
            .child(
                EmbeddedTypeComponent.create(c)
                    .name(place.getEmbeddedType())
                    .withLayout()
                    .widthDip(50)
            )
            .child(
                Text.create(c)
                    .text(place.getName())
                    .textSizeSp(18)
                    .withLayout()
                    .flex(1)
            )
            .build();
    }
}
