package com.amosyo.qfloat.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.amosyo.qfloat.controller.QFloatWindowSideslip;
import com.amosyo.qfloat.controller.QFloatWindowSideslipImpl;
import com.amosyo.qfloat.enums.QLocation;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-12 15:09
 * @description
 */
public class QFWSS {

    private QFloatWindowSideslip mQFloatWindowSideslip;

    private QFWSS(@NonNull Context context) {
        mQFloatWindowSideslip = new QFloatWindowSideslipImpl(requireNonNull(context));
    }

    public static QFWSS create(@NonNull Context context) {
        return new QFWSS(requireNonNull(context, "context is null!!!"));
    }


    public QFWSS setIndicatorView(final int indicatorViewRes) {
        mQFloatWindowSideslip.setIndicatorView(indicatorViewRes);
        return this;
    }

    public QFWSS setIndicatorView(@NonNull final View indicatorView) {
        mQFloatWindowSideslip.setIndicatorView(requireNonNull(indicatorView, "indicatorView cannot be null"));
        return this;
    }

    public QFWSS setContentView(final int contentViewRes) {
        mQFloatWindowSideslip.setContentView(contentViewRes);
        return this;
    }

    public QFWSS setContentView(@NonNull final View contentView) {
        mQFloatWindowSideslip.setContentView(requireNonNull(contentView));
        return this;
    }

    public QFWSS setContentLayoutBackgroundColor(final int color) {
        mQFloatWindowSideslip.setContentLayoutBackgroundColor(color);
        return this;
    }

    public QFWSS setContentLayoutBackground(@NonNull final Drawable drawable) {
        mQFloatWindowSideslip.setContentLayoutBackground(drawable);
        return this;
    }

    public QFWSS setIndicatorLocation(@QLocation.Locations final int location) {
        mQFloatWindowSideslip.setIndicatorLocation(location);
        return this;
    }

    public void show() {
        mQFloatWindowSideslip.showIndicator();
    }
}
