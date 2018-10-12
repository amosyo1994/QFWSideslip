package com.amosyo.qfloat.widget.content;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amosyo.qfloat.enums.QLocation;
import com.amosyo.qfloat.widget.content.bottom.QContentLayoutBottom;
import com.amosyo.qfloat.widget.content.left.QContentLayoutLeft;
import com.amosyo.qfloat.widget.content.right.QContentLayoutRight;
import com.amosyo.qfloat.widget.content.top.QContentLayoutTop;

import static java.util.Objects.requireNonNull;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-09 17:08
 * @description
 */
public class QContentLayoutFactory {

    @NonNull
    public static AbsQContentLayout create(@NonNull final Context context, final @QLocation.Locations int location) {
        requireNonNull(context);
        AbsQContentLayout rFloatContentLayout;
        switch (location) {
            case QLocation.LOCATION_SCREEN_LEFT:
                rFloatContentLayout = new QContentLayoutLeft(context);
                break;
            case QLocation.LOCATION_SCREEN_TOP:
                rFloatContentLayout = new QContentLayoutTop(context);
                break;
            case QLocation.LOCATION_SCREEN_RIGHT:
                rFloatContentLayout = new QContentLayoutRight(context);
                break;
            case QLocation.LOCATION_SCREEN_BOTTOM:
                rFloatContentLayout = new QContentLayoutBottom(context);
                break;
            default:
                throw new IllegalArgumentException("location invalid:" + location);
        }
        return rFloatContentLayout;
    }

}
