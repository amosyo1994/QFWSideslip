package com.amosyo.qfloat.widget.content;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amosyo.qfloat.callback.IQOnContentCallback;
import com.amosyo.qfloat.exception.QFloatContentRuntimeException;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-28 11:49
 * @description
 */
public abstract class AbsQContentLayout extends FrameLayout {

    protected static final long DURATION_ANIM = 300L;

    private IQOnContentCallback mFloatContentCallback;
    private Animator mCurrentAnimator;

    private AbsQContentController mFloatController;

    public AbsQContentLayout(@NonNull final Context context) {
        super(context);
        init();
    }

    public AbsQContentLayout(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mFloatController = requireNonNull(newFloatController());
    }

    @NonNull
    protected abstract AbsQContentController newFloatController();

    public void setOnFloatContentCallback(final IQOnContentCallback floatContentCallback) {
        mFloatContentCallback = floatContentCallback;
    }

    @Override
    public void addView(final View child, final int index, final ViewGroup.LayoutParams params) {
        final int childCount = getChildCount();
        if (childCount != 0) {
            throw new QFloatContentRuntimeException("can have only one child");
        }
        super.addView(child, index, params);
    }

    protected void verifyChild() {
        if (getChildCount() == 0) {
            throw new QFloatContentRuntimeException("u must setContentView");
        }
    }

    public abstract void offsetContent(final float diff);

    public abstract void adjustLayoutWhenMotionActionUp();

    public abstract void hideContent();

    public abstract void smoothShowContent();

    public abstract void smoothHideContent();

    @NonNull
    public AbsQContentController getFloatController() {
        return requireNonNull(mFloatController, "u must new FloatContentContent");
    }

    protected View getContentView() {
        return getChildAt(0);
    }

    protected void startHideAnim(final ObjectAnimator animator) {
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                super.onAnimationEnd(animation);
                if (null != mFloatContentCallback) {
                    mFloatContentCallback.onFloatContentHide();
                }
            }
        });
        startAnim(animator);
    }

    protected void startAnim(final Animator animator) {
        animator.start();
        mCurrentAnimator = animator;
    }

    protected void stopCurrentAnim() {
        if (null != mCurrentAnimator && mCurrentAnimator.isRunning()) {
            mCurrentAnimator.removeAllListeners();
            mCurrentAnimator.cancel();
        }
    }
}
