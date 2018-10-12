package com.amosyo.qfloat.controller;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.view.View;
import android.view.ViewGroup;

import com.amosyo.qfloat.enums.QLocation;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-27 09:55
 * @description
 */
public interface QFloatWindowSideslip {

    void setIndicatorView(@LayoutRes int indicatorViewRes);

    void setIndicatorView(@NonNull View indicatorView);

    void setContentView(@LayoutRes int contentViewRes);

    void setContentView(@NonNull View contentView);

    void setContentLayoutBackgroundColor(@ColorInt int color);

    void setContentLayoutBackground(@NonNull Drawable drawable);

    void setIndicatorLocation(@QLocation.Locations int location);

    void showIndicator();
}
