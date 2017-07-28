package com.facebook.samples.litho.playground;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.EditText;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.TextChangedEvent;
import com.facebook.samples.litho.R;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;

import static android.widget.ImageView.ScaleType;

@LayoutSpec
public class TextInputComponentSpec {
    @PropDefault static final int iconColor = -1;

    @OnCreateLayout
    static ComponentLayout onCreateLayout(
        ComponentContext c,
        @Prop(resType = ResType.DRAWABLE) final Drawable icon,
        @Prop(optional = true, resType = ResType.COLOR) int iconColor) {

        if (iconColor == -1) {
            iconColor = c.getColor(R.color.primary);
        }
        icon.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP);

        return Row.create(c)
            .testKey("container")
            .alignItems(YogaAlign.CENTER)
            .heightDip(60)
            .marginDip(YogaEdge.LEFT, 12)
            .marginDip(YogaEdge.RIGHT, 12)
            .backgroundRes(android.R.color.white)
            .child(
                Image.create(c)
                    .key("icon")
                    .scaleType(ScaleType.FIT_CENTER)
                    .drawable(icon)
                    .withLayout()
                    .flex(1)
                    .widthDip(22)
                    .heightDip(22)
                    .paddingDip(YogaEdge.LEFT, 10)
                    .paddingDip(YogaEdge.RIGHT, 10)
            )
            .child(
                EditText.create(c)
                    .key("textInput")
                    .textSizeSp(14)
                    .textChangedEventHandler(TextInputComponent.onChange(c))
                    .withLayout()
                    .flex(8)
            )
            .build();
    }

    @OnEvent(TextChangedEvent.class)
    static void onChange(
        ComponentContext c,
        @FromEvent String text,
        @Prop OnQueryUpdateListener queryListener) {
        Log.d("TextInputComponentSpec", text);
        queryListener.onQueryUpdated(text);
    }

    public interface OnQueryUpdateListener {
        void onQueryUpdated(String query);
    }
}
