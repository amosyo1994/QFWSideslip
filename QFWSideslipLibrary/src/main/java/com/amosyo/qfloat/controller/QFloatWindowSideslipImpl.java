package com.amosyo.qfloat.controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.amosyo.qfloat.callback.IQOnEventCallback;
import com.amosyo.qfloat.widget.content.IQOnContentControllerCallback;
import com.amosyo.qfloat.widget.content.AbsQContentController;
import com.amosyo.qfloat.enums.QSideslipState;
import com.amosyo.qfloat.enums.QLocation;
import com.amosyo.qfloat.helper.QTouchParseHelper;
import com.amosyo.qfloat.widget.content.AbsQContentLayout;
import com.amosyo.qfloat.widget.QIndicatorLayout;
import com.amosyo.qfloat.widget.content.QContentLayoutFactory;
import com.amosyo.qfloat.utils.QViewUtils;


import static java.util.Objects.requireNonNull;


/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-27 09:55
 * @description
 */
public class QFloatWindowSideslipImpl implements QFloatWindowSideslip {

    @NonNull
    private Context mContext;
    @NonNull
    private WindowManager mWindowManager;

    private QIndicatorLayout mIndicatorLayout;
    private boolean mIsIndicatorAdded;
    private View mIndicatorView;
    private WindowManager.LayoutParams mIndicatorLp;

    private AbsQContentLayout mContentLayout;
    private View mContentView;
    private WindowManager.LayoutParams mFloatContentViewLp;
    private Drawable mContentLayoutBackgroundDrawable;

    private int mLocation;

    private AbsQContentController mFloatController;

    @QSideslipState.States
    private int mState = QSideslipState.STATE_INDICATOR;


    public QFloatWindowSideslipImpl(@NonNull final Context context) {
        mContext = requireNonNull(context, "context not is null").getApplicationContext();
        mWindowManager = requireNonNull(
                (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)
        );
    }

    private void initFloatWindowViewLp() {
        if (null != mFloatContentViewLp) {
            return;
        }
        mFloatContentViewLp = new WindowManager.LayoutParams();
        mFloatContentViewLp.packageName = mContext.getPackageName();
        mFloatContentViewLp.width = WindowManager.LayoutParams.MATCH_PARENT;
        mFloatContentViewLp.height = WindowManager.LayoutParams.MATCH_PARENT;
        mFloatContentViewLp.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mFloatContentViewLp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mFloatContentViewLp.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mFloatContentViewLp.format = PixelFormat.TRANSPARENT;
    }

    @Override
    public void setIndicatorView(final int indicatorViewRes) {
        initIndicatorLayout();
        final View view = LayoutInflater.from(mContext).inflate(indicatorViewRes, mIndicatorLayout, false);
        setIndicatorView(view);
    }

    @Override
    public void setIndicatorView(@NonNull final View indicatorView) {
        mIndicatorView = requireNonNull(indicatorView, "indicatorView not is null");
        initIndicatorLayout();
        mIndicatorLayout.addView(indicatorView);
        new QTouchParseHelper(indicatorView).setOnMoveCallback(mMoveCallback);
        indicatorView.setOnClickListener((view) -> {
            if (null != mFloatController) {
                mFloatController.onClick(view);
            }
        });
    }

    private void initIndicatorLayout() {
        if (null == mIndicatorLayout) {
            mIndicatorLayout = new QIndicatorLayout(mContext);
        }
    }

    private IQOnEventCallback mMoveCallback = new IQOnEventCallback() {

        @Override
        public void onMoveLeft(final MotionEvent event, final float distanceX, final float distanceY) {
            getFloatController().onMoveLeft(event, distanceX, distanceY);
        }

        @Override
        public void onMoveRight(final MotionEvent event, final float distanceX, final float distanceY) {
            getFloatController().onMoveRight(event, distanceX, distanceY);
        }

        @Override
        public void onMoveTop(final MotionEvent event, final float distanceX, final float distanceY) {
            getFloatController().onMoveTop(event, distanceX, distanceY);
        }

        @Override
        public void onMoveBottom(final MotionEvent event, final float distanceX, final float distanceY) {
            getFloatController().onMoveBottom(event, distanceX, distanceY);
        }

        @Override
        public void onActionDown(final MotionEvent event) {
            getFloatController().onActionDown(event);
        }

        @Override
        public void onActionUp(final MotionEvent event) {
            getFloatController().onActionUp(event);
        }
    };

    private AbsQContentController getFloatController() {
        return requireNonNull(mFloatController, "u must call setIndicatorLocation");
    }

    private void showFloatContentLayout() {
        mIndicatorView.setVisibility(View.INVISIBLE);
        if (mState == QSideslipState.STATE_CONTENT) {
            return;
        }
        initFloatWindowViewLp();
        mWindowManager.addView(mContentLayout, mFloatContentViewLp);
        mContentLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mContentLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                mContentLayout.hideContent();
                return true;
            }
        });
        mState = QSideslipState.STATE_CONTENT;
    }

    @Override
    public void setContentView(@LayoutRes final int contentViewRes) {
        mContentView = LayoutInflater.from(mContext).inflate(contentViewRes, mContentLayout, true);
    }

    @Override
    public void setContentView(@NonNull final View contentView) {
        mContentView = requireNonNull(contentView);
        if (null != mContentLayout) {
            QViewUtils.addViewSafe(mContentLayout, contentView);
        }
    }

    @Override
    public void setContentLayoutBackgroundColor(final int color) {
        setContentLayoutBackground(new ColorDrawable(color));
    }

    @Override
    public void setContentLayoutBackground(@NonNull final Drawable drawable) {
        this.mContentLayoutBackgroundDrawable = requireNonNull(drawable);
        if (null != mContentLayout) {
            mContentLayout.setBackground(drawable);
        }
    }

    private void initFloatContentLayout() {
        final View contentView = mContentView;
        final AbsQContentLayout contentLayout = mContentLayout = QContentLayoutFactory.create(contentView.getContext(), mLocation);
        contentLayout.setOnClickListener(v -> contentLayout.smoothHideContent());
        contentLayout.setOnFloatContentCallback(() -> {
            if (mState == QSideslipState.STATE_CONTENT) {
                mWindowManager.removeView(contentLayout);
                showIndicator();
            }
        });
        if (null == mContentLayoutBackgroundDrawable) {
            mContentLayoutBackgroundDrawable = new ColorDrawable(Color.parseColor("#50888888"));
        }
        contentLayout.setBackground(mContentLayoutBackgroundDrawable);
        QViewUtils.addViewSafe(contentLayout, contentView);
        initFloatController();
    }

    private void initFloatController() {
        final AbsQContentLayout floatContentLayout = mContentLayout;
        final AbsQContentController floatController = floatContentLayout.getFloatController();
        floatController.setOnStrategyCallback(new IQOnContentControllerCallback() {
            @Override
            public void onShowFloatContent() {
                showFloatContentLayout();
            }

            @Override
            public void onIndicatorLocation(final int distanceX, final int distanceY) {
                if (mState == QSideslipState.STATE_INDICATOR) {
                    mIndicatorLp.x = mIndicatorLp.x + distanceX;
                    mIndicatorLp.y = mIndicatorLp.y + distanceY;
                    mWindowManager.updateViewLayout(mIndicatorLayout, mIndicatorLp);
                }
            }
        });
        mFloatController = floatController;
    }

    @Override
    public void setIndicatorLocation(@QLocation.Locations final int location) {
        int gravity;
        this.mLocation = location;
        initFloatContentLayout();
        switch (location) {
            case QLocation.LOCATION_SCREEN_TOP:
                gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
                break;
            case QLocation.LOCATION_SCREEN_BOTTOM:
                gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                break;
            case QLocation.LOCATION_SCREEN_LEFT:
                gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                break;
            case QLocation.LOCATION_SCREEN_RIGHT:
                gravity = Gravity.END | Gravity.CENTER_VERTICAL;
                break;
            default:
                throw new IllegalArgumentException("locations is invalid:" + location);
        }
        initIndicatorLp();
        mIndicatorLp.gravity = gravity;
        mIndicatorLayout.setGravity(gravity);
    }

    @Override
    public void showIndicator() {
        final View indicatorLayout = mIndicatorLayout;
        initIndicatorLp();
        if (!mIsIndicatorAdded) {
            mWindowManager.addView(indicatorLayout, mIndicatorLp);
            mIsIndicatorAdded = true;
        }
        mIndicatorView.setVisibility(View.VISIBLE);
        mState = QSideslipState.STATE_INDICATOR;
    }

    private void initIndicatorLp() {
        if (null != mIndicatorLp) {
            return;
        }
        mIndicatorLp = new WindowManager.LayoutParams();
        mIndicatorLp.packageName = mContext.getPackageName();
        mIndicatorLp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mIndicatorLp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mIndicatorLp.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mIndicatorLp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mIndicatorLp.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mIndicatorLp.format = PixelFormat.RGBA_8888;
    }
}
