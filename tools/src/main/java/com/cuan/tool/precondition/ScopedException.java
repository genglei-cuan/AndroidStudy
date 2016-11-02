package com.cuan.tool.precondition;

/**
 * Created by genglei-cuan on 16-11-2.
 */

public class ScopedException extends RuntimeException {

    /**
     * 其中一个作用是可以启动tag的作用，快速定位索引到log信息
     */
    public static final String ERROR_LOG_PREFIX = "**** data scoped error <<<< ";
    public static final String ERROR_LOG_SUFFIX = " >>>> data scoped error ****";


    public ScopedException(String message,Object... args){
        super(message == null ? "unknown data scoped exception" : String.format(message, args));
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(ERROR_LOG_PREFIX)
                .append(super.getMessage())
                .append(ERROR_LOG_SUFFIX);

        return sb.toString();
    }
}
