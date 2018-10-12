package com.amosyo.qfloat.widget.content.left;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.amosyo.qfloat.widget.content.AbsQContentController;
import com.amosyo.qfloat.widget.content.AbsQContentLayout;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-08 15:12
 * @description
 */
public class QContentControllerLeft extends AbsQContentController {

    public QContentControllerLeft(@NonNull final AbsQContentLayout floatContentLayout) {
        super(floatContentLayout);
    }

    @Override
    public void onMoveLeft(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onShowFloatContent();
        mFloatContentLayout.offsetContent(distanceX * 2);
        mIsToggleLayout = true;
        mIsMove = true;
    }

    @Override
    public void onMoveRight(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onShowFloatContent();
        mFloatContentLayout.offsetContent(distanceX * 2);
        mIsMove = true;
        mIsToggleLayout = true;
    }

    @Override
    public void onMoveTop(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onIndicatorLocation(0, (int) distanceY);
        mIsMove = true;
    }

    @Override
    public void onMoveBottom(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onIndicatorLocation(0, (int) distanceY);
        mIsMove = true;
    }
}
