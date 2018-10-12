package com.amosyo.qfloat.exception;

/**
 * @author WangQinyu
 * @email amosyo1994@gmail.com
 * @Date 2018-09-29 10:31
 * @description
 */
public class QFloatContentRuntimeException extends RuntimeException {

    public QFloatContentRuntimeException() {
    }

    public QFloatContentRuntimeException(final String message) {
        super(message);
    }

    public QFloatContentRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QFloatContentRuntimeException(final Throwable cause) {
        super(cause);
    }
}
