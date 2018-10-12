package com.amosyo.qfloat.widget.content;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-08 15:15
 * @description
 */
public interface IQOnContentControllerCallback {

    void onShowFloatContent();

    void onIndicatorLocation(int distanceX, int distanceY);

    IQOnContentControllerCallback EMPTY_ON_STRATEGY_CALL_BACK = new IQOnContentControllerCallback() {
        @Override
        public void onShowFloatContent() {

        }

        @Override
        public void onIndicatorLocation(final int distanceX, final int distanceY) {

        }
    };
}
