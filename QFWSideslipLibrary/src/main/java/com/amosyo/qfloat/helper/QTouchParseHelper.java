package com.amosyo.qfloat.helper;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.amosyo.qfloat.callback.IQOnEventCallback;

import static java.lang.Math.abs;
import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-27 14:11
 * @description
 */
public class QTouchParseHelper {

    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_MOVE_LEFT = TOUCH_STATE_NONE + 1;
    private static final int TOUCH_STATE_MOVE_RIGHT = TOUCH_STATE_MOVE_LEFT + 1;
    private static final int TOUCH_STATE_MOVE_TOP = TOUCH_STATE_MOVE_RIGHT + 1;
    private static final int TOUCH_STATE_MOVE_BOTTOM = TOUCH_STATE_MOVE_TOP + 1;

    @IntDef({TOUCH_STATE_NONE, TOUCH_STATE_MOVE_LEFT, TOUCH_STATE_MOVE_TOP, TOUCH_STATE_MOVE_RIGHT, TOUCH_STATE_MOVE_BOTTOM})
    private @interface TouchStates {

    }

    private IQOnEventCallback mOnMoveCallback;
    @TouchStates
    private int mTouchState = TOUCH_STATE_NONE;

    public QTouchParseHelper(@NonNull View indicatorView) {
        requireNonNull(indicatorView, "indicatorView is null")
                .setOnTouchListener(this::onTouchEvent);
    }

    private float mLastX;
    private float mLastY;

    private boolean onTouchEvent(final View view, final MotionEvent event) {
        boolean rOnTouchEvent = false;
        final float x = event.getRawX();
        final float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchState = TOUCH_STATE_NONE;
                getOnMoveCallback().onActionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                final float distanceX = x - mLastX;
                final float distanceY = y - mLastY;
                final float absDiffX = abs(distanceX);
                final float absDiffY = abs(distanceY);
//                if (absDiffX < mScaledTouchSlop && absDiffY < mScaledTouchSlop) {
//                    return true;
//                }
                switch (mTouchState) {
                    case TOUCH_STATE_NONE:
                        if (absDiffX > absDiffY) {
                            mTouchState = distanceX > 0 ? TOUCH_STATE_MOVE_RIGHT : TOUCH_STATE_MOVE_LEFT;
                        } else {
                            mTouchState = distanceY > 0 ? TOUCH_STATE_MOVE_BOTTOM : TOUCH_STATE_MOVE_TOP;
                        }
                        break;
                    case TOUCH_STATE_MOVE_LEFT:
                        mTouchState = distanceX > 0 ? TOUCH_STATE_MOVE_RIGHT : TOUCH_STATE_MOVE_LEFT;
                        break;
                    case TOUCH_STATE_MOVE_TOP:
                        mTouchState = distanceY > 0 ? TOUCH_STATE_MOVE_BOTTOM : TOUCH_STATE_MOVE_TOP;
                        break;
                    case TOUCH_STATE_MOVE_RIGHT:
                        mTouchState = distanceX > 0 ? TOUCH_STATE_MOVE_RIGHT : TOUCH_STATE_MOVE_LEFT;
                        break;
                    case TOUCH_STATE_MOVE_BOTTOM:
                        mTouchState = distanceY > 0 ? TOUCH_STATE_MOVE_BOTTOM : TOUCH_STATE_MOVE_TOP;
                        break;
                    default:
                        throw new IllegalArgumentException("mTouchState error:" + mTouchState);
                }
                switch (mTouchState) {
                    case TOUCH_STATE_MOVE_LEFT:
                        getOnMoveCallback().onMoveLeft(event, distanceX, distanceY);
                        break;
                    case TOUCH_STATE_MOVE_TOP:
                        getOnMoveCallback().onMoveTop(event, distanceX, distanceY);
                        break;
                    case TOUCH_STATE_MOVE_RIGHT:
                        getOnMoveCallback().onMoveRight(event, distanceX, distanceY);
                        break;
                    case TOUCH_STATE_MOVE_BOTTOM:
                        getOnMoveCallback().onMoveBottom(event, distanceX, distanceY);
                        break;
                    case TOUCH_STATE_NONE:
                    default:
                        throw new IllegalArgumentException("mTouchState error:" + mTouchState);
                }
                rOnTouchEvent = true;
                break;
            case MotionEvent.ACTION_UP:
                getOnMoveCallback().onActionUp(event);
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return rOnTouchEvent;
    }

    public void setOnMoveCallback(final IQOnEventCallback onMoveCallback) {
        this.mOnMoveCallback = onMoveCallback;
    }

    @NonNull
    private IQOnEventCallback getOnMoveCallback() {
        return null != mOnMoveCallback ? mOnMoveCallback : IQOnEventCallback.EMPTY;
    }
}
