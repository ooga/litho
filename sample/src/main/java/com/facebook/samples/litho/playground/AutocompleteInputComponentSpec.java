package com.facebook.samples.litho.playground;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.samples.litho.AutocompleteActivity;
import com.facebook.samples.litho.R;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;

import static android.widget.ImageView.ScaleType;

@LayoutSpec
public class AutocompleteInputComponentSpec {
    @PropDefault static final int iconColor = -1;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop(resType = ResType.DRAWABLE) final Drawable icon,
            @Prop(optional = true, resType = ResType.COLOR) int iconColor,
            @Prop(optional = true) final String title,
            @Prop final String placeholder) {

        if (iconColor == -1) {
            iconColor = c.getColor(R.color.primary);
        }
        icon.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP);

        return Row.create(c)
            .testKey("input")
            .alignItems(YogaAlign.CENTER)
            .heightDip(60)
            .child(
                Image.create(c)
                    .key("icon")
                    .scaleType(ScaleType.FIT_CENTER)
                    .drawable(icon)
                    .withLayout()
                    .widthDip(22)
                    .heightDip(22)
                    .flex(1)
                    .paddingDip(YogaEdge.LEFT, 10)
                    .paddingDip(YogaEdge.RIGHT, 10)
            )
            .child(
                Column.create(c)
                    .testKey("description")
                    .flex(8)
                    .child(
                        Text.create(c)
                            .key("title")
                            .text(title)
                            .textSizeSp(14)
                    )
                    .child(
                        Text.create(c)
                            .key("placeholder")
                            .text(placeholder)
                            .textColorRes(R.color.light)
                            .textSizeSp(14)
                    )
            )
            .clickHandler(AutocompleteInputComponent.onClick(c))
            .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(
        ComponentContext c,
        @FromEvent View view) {
        final Intent intent = new Intent(c, AutocompleteActivity.class);
//        intent.putExtra("demoName", name);
        c.startActivity(intent);
    }
}