package com.facebook.samples.litho.playground;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.widget.CardClip;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.samples.litho.R;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaPositionType;

import static android.widget.ImageView.ScaleType.FIT_XY;

@LayoutSpec
public class BigButtonComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop final String text,
            @Prop(resType = ResType.DRAWABLE) final Drawable icon) {

        icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        return Column.create(c)
            .paddingDip(YogaEdge.TOP, 28)
            .paddingDip(YogaEdge.BOTTOM, 28)
            .justifyContent(YogaJustify.CENTER)
            .backgroundColor(c.getResources().getColor(R.color.tertiary))
            .flex(1)
            .child(
                Column.create(c)
                    .justifyContent(YogaJustify.CENTER)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Image.create(c)
                            .scaleType(FIT_XY)
                            .drawable(icon)
                            .withLayout()
                            .widthDip(32)
                            .heightDip(32)
                    )
                    .child(
                        Text.create(c)
                            .text(text)
                            .textColor(Color.WHITE)
                            .textStyle(Typeface.BOLD)
                            .textSizeSp(14)
                            .withLayout()
                            .marginDip(YogaEdge.TOP, 16)
                    )
            )
            .child(
                CardClip.create(c)
                    .clippingColorRes(R.color.secondary)
                    .cornerRadiusDip(5)
                    .withLayout()
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionPx(YogaEdge.ALL, 0)
            )
            .build();
    }
}
