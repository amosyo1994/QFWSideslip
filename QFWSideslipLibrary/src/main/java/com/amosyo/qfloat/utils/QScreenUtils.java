package com.amosyo.qfloat.utils;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-29 13:54
 * @description
 */
public class QScreenUtils {

    public static int getScreenWidth(@NonNull final Context context) {
        requireNonNull(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    public static int getScreenHeight(@NonNull final Context context) {
        requireNonNull(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    public static int dp2px(@NonNull final Context context, final float dpValue) {
        requireNonNull(context);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(@NonNull final Context context, final float pxValue) {
        requireNonNull(context);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
