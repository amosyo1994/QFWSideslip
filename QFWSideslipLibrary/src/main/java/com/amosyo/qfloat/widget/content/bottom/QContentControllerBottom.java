package com.amosyo.qfloat.widget.content.bottom;

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
public class QContentControllerBottom extends AbsQContentController {

    public QContentControllerBottom(@NonNull final AbsQContentLayout floatContentLayout) {
        super(floatContentLayout);
    }

    @Override
    public void onMoveLeft(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onIndicatorLocation((int) distanceX, 0);
        mIsMove = true;
    }

    @Override
    public void onMoveRight(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onIndicatorLocation((int) distanceX, 0);
        mIsMove = true;
    }

    @Override
    public void onMoveTop(final MotionEvent event, final float distanceX, final float distanceY) {
        getOnStrategyCallback().onShowFloatContent();
        mFloatContentLayout.offsetContent(distanceY * 2);
        mIsToggleLayout = true;
        mIsMove = true;

    }

    @Override
    public void onMoveBottom(final MotionEvent event, final float distanceX, final float distanceY) {
        onMoveTop(event, distanceX, distanceY);
    }
}
