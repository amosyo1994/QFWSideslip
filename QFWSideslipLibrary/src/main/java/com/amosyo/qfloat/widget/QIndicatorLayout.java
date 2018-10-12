package com.amosyo.qfloat.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amosyo.qfloat.exception.QIndicatorRuntimeException;

import static java.lang.Math.abs;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-28 14:54
 * @description
 */
public class QIndicatorLayout extends FrameLayout {

    public QIndicatorLayout(@NonNull final Context context) {
        super(context);
    }

    public QIndicatorLayout(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(final View child, final int index, final ViewGroup.LayoutParams params) {
        if (getChildCount() != 0) {
            throw new QIndicatorRuntimeException("can have only one child");
        }
        super.addView(child, index, params);
    }

    public void setGravity(final int gravity) {
        if (getChildCount() == 0) {
            throw new QIndicatorRuntimeException("u must setContentView");
        }
        final View indicatorView = getChildAt(0);
        QIndicatorLayout.LayoutParams layoutParams = (LayoutParams) indicatorView.getLayoutParams();
        layoutParams.gravity = gravity;
        indicatorView.requestLayout();
    }

    private void verifyIndicatorView() {
        if (getChildCount() == 0) {
            throw new QIndicatorRuntimeException("u must setIndicatorView");
        }
    }

    private View getIndicatorView() {
        return getChildAt(0);
    }

}
