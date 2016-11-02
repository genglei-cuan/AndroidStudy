package com.cuan.tool.precondition;

/**
 * Created by genglei-cuan on 16-11-1.
 */

public class Preconditions {

    /**
     * 如果 value 为 false 时，抛出运行时异常
     * @param value
     * @param error
     * @param args
     */
    public static void check(boolean value, String error, Object... args) {
        if (!value) {
            ThrowScopedException(error,args);
        }
    }

    /**
     * 如果 value 为 null 时，抛出运行时异常
     * @param value
     * @param error
     * @param args
     */
    public static void checkNotNull(Object value, String error, Object... args) {
        if (value == null) {
            ThrowScopedException(error,args);
        }
    }

    /**
     * 如果 value 不为 null 时，抛出运行时异常
     * @param value
     * @param error
     * @param args
     */
    public static void checkNull(Object value, String error, Object... args) {
        if (value != null) {
            ThrowScopedException(error,args);
        }
    }

    private static void ThrowScopedException(String message,Object... args){
        ScopedException se = new ScopedException(message,args);
        throw se;
    }
}
