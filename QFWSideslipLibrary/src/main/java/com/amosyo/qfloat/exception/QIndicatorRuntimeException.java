package com.amosyo.qfloat.exception;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-30 16:13
 * @description
 */
public class QIndicatorRuntimeException extends RuntimeException {

    public QIndicatorRuntimeException() {
    }

    public QIndicatorRuntimeException(final String message) {
        super(message);
    }

    public QIndicatorRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QIndicatorRuntimeException(final Throwable cause) {
        super(cause);
    }
}
