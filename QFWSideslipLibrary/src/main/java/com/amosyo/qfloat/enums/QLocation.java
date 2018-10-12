package com.amosyo.qfloat.enums;

import android.support.annotation.IntDef;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-27 10:07
 * @description
 */
public class QLocation {

    public final static int LOCATION_SCREEN_TOP = 0;
    public final static int LOCATION_SCREEN_LEFT = 1;
    public final static int LOCATION_SCREEN_RIGHT = 2;
    public final static int LOCATION_SCREEN_BOTTOM = 3;

    @IntDef({
            LOCATION_SCREEN_TOP,
            LOCATION_SCREEN_BOTTOM,
            LOCATION_SCREEN_LEFT,
            LOCATION_SCREEN_RIGHT
    })
    public @interface Locations {

    }

}
