package com.amosyo.qfloat.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-09 17:15
 * @description
 */
public class QViewUtils {

    public static void addViewSafe(ViewGroup parentView, View view) {
        final ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        parentView.addView(view);
    }
}
