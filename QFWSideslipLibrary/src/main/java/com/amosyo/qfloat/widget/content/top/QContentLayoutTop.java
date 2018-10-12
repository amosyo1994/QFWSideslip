package com.amosyo.qfloat.widget.content.top;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.amosyo.qfloat.widget.content.AbsQContentLayout;
import com.amosyo.qfloat.widget.content.AbsQContentController;
import com.amosyo.qfloat.utils.QScreenUtils;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-28 11:49
 * @description
 */
public class QContentLayoutTop extends AbsQContentLayout {

    public QContentLayoutTop(@NonNull final Context context) {
        super(context);
    }

    public QContentLayoutTop(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    protected AbsQContentController newFloatController() {
        return new QContentControllerTop(this);
    }

    @Override
    public void offsetContent(final float diff) {
        verifyChild();
        final View contentView = getContentView();
        final int screenHeight = QScreenUtils.getScreenHeight(getContext());
        int bottom = contentView.getBottom();
        int newBottom = (int) (bottom + diff);
        if (newBottom < 0) {
            newBottom = 0;
        } else if (newBottom > screenHeight) {
            newBottom = screenHeight;
        }
        if (newBottom == bottom) {
            return;
        }
        Log.e("TAG", "offsetContent:" + diff);
        contentView.setBottom(newBottom);
    }

    /**
     * 两种情况
     * 1.内容显示区域小于50%，开启滑动动画滚回去
     * 2.内容显示区域大于50%，小于100%，开启滑动动画滚到全屏
     */
    @Override
    public void adjustLayoutWhenMotionActionUp() {
        verifyChild();
        final View contentView = getContentView();
        final int screenHeight = QScreenUtils.getScreenHeight(getContext());
        final int bottom = contentView.getBottom();
        if (bottom < screenHeight / 2) {
            smoothHideContent();
        } else {
            smoothShowContent();
        }
    }

    @Override
    public void hideContent() {
        verifyChild();
        final View contentView = getContentView();
        contentView.setBottom(0);
        Log.e("TAG", "hideContent:");
    }


    @Override
    public void smoothShowContent() {
        verifyChild();
        stopCurrentAnim();
        final View contentView = getContentView();
        final int screenHeight = QScreenUtils.getScreenHeight(getContext());
        final ObjectAnimator animator = ObjectAnimator
                .ofInt(contentView, "bottom", contentView.getBottom(), screenHeight)
                .setDuration(DURATION_ANIM);
        startAnim(animator);
        Log.e("TAG", "smoothShowContent:");
    }

    @Override
    public void smoothHideContent() {
        verifyChild();
        stopCurrentAnim();
        final View contentView = getContentView();
        final ObjectAnimator animator = ObjectAnimator
                .ofInt(contentView, "bottom", contentView.getBottom(), 0)
                .setDuration(DURATION_ANIM);
        startHideAnim(animator);
        Log.e("TAG", "smoothHideContent:" + contentView.getBottom());
    }
}
