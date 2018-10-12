package com.amosyo.qfloat.widget.content;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.amosyo.qfloat.callback.IQOnEventCallback;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-08 15:08
 * @description
 */
public abstract class AbsQContentController implements IQOnEventCallback, View.OnClickListener {

    protected final AbsQContentLayout mFloatContentLayout;
    private IQOnContentControllerCallback mOnStrategyCallback;

    protected boolean mIsToggleLayout;
    protected boolean mIsMove;

    protected AbsQContentController(@NonNull final AbsQContentLayout floatContentLayout) {
        mFloatContentLayout = requireNonNull(floatContentLayout);
    }

    public void setOnStrategyCallback(final IQOnContentControllerCallback callback) {
        this.mOnStrategyCallback = callback;
    }

    protected IQOnContentControllerCallback getOnStrategyCallback() {
        return null != mOnStrategyCallback ? mOnStrategyCallback : IQOnContentControllerCallback.EMPTY_ON_STRATEGY_CALL_BACK;
    }

    @Override
    public void onActionDown(final MotionEvent event) {
        mIsToggleLayout = false;
        mIsMove = false;
    }

    @Override
    public void onActionUp(final MotionEvent event) {
        if (mIsToggleLayout) {
            mFloatContentLayout.adjustLayoutWhenMotionActionUp();
        }
    }

    @Override
    public void onClick(final View v) {
        if (!mIsMove) {
            if (null != mOnStrategyCallback) {
                mOnStrategyCallback.onShowFloatContent();
            }
            mFloatContentLayout.post(mFloatContentLayout::smoothShowContent);
        }
    }
}
