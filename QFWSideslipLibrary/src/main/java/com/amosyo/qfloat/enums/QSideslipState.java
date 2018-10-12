package com.amosyo.qfloat.enums;

import android.support.annotation.IntDef;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-10-08 11:20
 * @description
 */
public class QSideslipState {

    public static final int STATE_INDICATOR = 0;
    public static final int STATE_CONTENT = 1;

    @IntDef({STATE_INDICATOR, STATE_CONTENT})
    public @interface States {

    }
}
