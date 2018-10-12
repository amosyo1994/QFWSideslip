package com.amosyo.qfloat.widget.content.right;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.amosyo.qfloat.widget.content.AbsQContentLayout;
import com.amosyo.qfloat.widget.content.AbsQContentController;
import com.amosyo.qfloat.utils.QScreenUtils;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-28 11:49
 * @description
 */
public class QContentLayoutRight extends AbsQContentLayout {

    public QContentLayoutRight(@NonNull final Context context) {
        super(context);
    }

    public QContentLayoutRight(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    protected AbsQContentController newFloatController() {
        return new QContentControllerRight(this);
    }

    @Override
    public void offsetContent(final float diff) {
        verifyChild();
        final View contentView = getContentView();
        final int screenWidth = QScreenUtils.getScreenWidth(getContext());
        final int left = contentView.getLeft();
        int newLeft = (int) (left + diff);
        if (newLeft < 0) {
            newLeft = 0;
        } else if (newLeft > screenWidth) {
            newLeft = screenWidth;
        }
        if (newLeft == left) {
            return;
        }
        contentView.setLeft(newLeft);
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
        final int left = contentView.getLeft();
        if (left < screenWidth / 2) {
            smoothShowContent();
        } else {
            smoothHideContent();
        }
    }

    @Override
    public void hideContent() {
        verifyChild();
        getContentView().setLeft(QScreenUtils.getScreenWidth(getContext()));
    }


    @Override
    public void smoothShowContent() {
        verifyChild();
        final View contentView = getContentView();
        stopCurrentAnim();
        final ObjectAnimator animator = ObjectAnimator.ofInt(contentView, "left", contentView.getLeft(), 0)
                .setDuration(DURATION_ANIM);
        startAnim(animator);
    }

    @Override
    public void smoothHideContent() {
        verifyChild();
        stopCurrentAnim();
        final View contentView = getContentView();
        final int screenWidth = QScreenUtils.getScreenWidth(getContext());
        final ObjectAnimator animator = ObjectAnimator.ofInt(contentView, "left", contentView.getLeft(), screenWidth)
                .setDuration(DURATION_ANIM);
        startHideAnim(animator);
    }
}
