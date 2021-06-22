package com.siberteam.edu.zernest.ranker.interfaces;

public interface IErrorHandler extends ILogger {
    default void handleException(Exception e) {
        log(e + "\n" + e.getCause());
        System.exit(1);
    }
}