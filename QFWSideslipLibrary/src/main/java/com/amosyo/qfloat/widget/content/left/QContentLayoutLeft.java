package com.amosyo.qfloat.widget.content.left;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
public class QContentLayoutLeft extends AbsQContentLayout {

    public QContentLayoutLeft(@NonNull final Context context) {
        super(context);
    }

    public QContentLayoutLeft(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    protected AbsQContentController newFloatController() {
        return new QContentControllerLeft(this);
    }

    @Override
    public void offsetContent(final float diff) {
        verifyChild();
        final View contentView = getContentView();
        final int screenWidth = QScreenUtils.getScreenWidth(getContext());
        int right = contentView.getRight();
        int newRight = (int) (right + diff);
        if (newRight < 0) {
            newRight = 0;
        } else if (newRight > screenWidth) {
            newRight = screenWidth;
        }
        if (newRight == right) {
            return;
        }
        contentView.setLeft(newRight - QScreenUtils.getScreenWidth(getContext()));
        contentView.setRight(newRight);
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
        final int screenWidth = QScreenUtils.getScreenWidth(getContext());
        final int right = contentView.getRight();
        if (right < screenWidth / 2) {
            smoothHideContent();
        } else {
            smoothShowContent();
        }
    }

    @Override
    public void hideContent() {
        verifyChild();
        final View contentView = getContentView();
        contentView.setRight(0);
    }


    @Override
    public void smoothShowContent() {
        verifyChild();
        final View contentView = getContentView();
        final int screenWidth = QScreenUtils.getScreenWidth(getContext());
        stopCurrentAnim();
        final ObjectAnimator rightAnimator = ObjectAnimator.ofInt(contentView, "right", contentView.getRight(), screenWidth)
                .setDuration(DURATION_ANIM);
        final ObjectAnimator leftAnimator = ObjectAnimator.ofInt(contentView, "left", contentView.getLeft(), 0)
                .setDuration(DURATION_ANIM);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rightAnimator).with(leftAnimator);
        startAnim(animatorSet);
    }

    @Override
    public void smoothHideContent() {
        verifyChild();
        stopCurrentAnim();
        final View contentView = getContentView();
        final ObjectAnimator animator = ObjectAnimator.ofInt(contentView, "right", contentView.getRight(), 0)
                .setDuration(DURATION_ANIM);
        startHideAnim(animator);
    }
}
