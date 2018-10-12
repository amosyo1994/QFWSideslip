package com.amosyo.qfloat.callback;

import android.view.MotionEvent;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-27 16:39
 * @description
 */
public interface IQOnEventCallback {

    void onMoveLeft(MotionEvent event, float distanceX, float distanceY);

    void onMoveRight(MotionEvent event, float distanceX, float distanceY);

    void onMoveTop(MotionEvent event, float distanceX, float distanceY);

    void onMoveBottom(MotionEvent event, float distanceX, float distanceY);

    void onActionDown(MotionEvent event);

    void onActionUp(MotionEvent event);

    IQOnEventCallback EMPTY = new IQOnEventCallback() {
        @Override
        public void onMoveLeft(final MotionEvent event, final float distanceX, final float distanceY) {

        }

        @Override
        public void onMoveRight(final MotionEvent event, final float distanceX, final float distanceY) {

        }

        @Override
        public void onMoveTop(final MotionEvent event, final float distanceX, final float distanceY) {

        }

        @Override
        public void onMoveBottom(final MotionEvent event, final float distanceX, final float distanceY) {

        }

        @Override
        public void onActionDown(final MotionEvent event) {

        }

        @Override
        public void onActionUp(final MotionEvent event) {

        }
    };
}
