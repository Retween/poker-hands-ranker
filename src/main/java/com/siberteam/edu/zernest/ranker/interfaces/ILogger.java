package com.siberteam.edu.zernest.ranker.interfaces;

public interface ILogger {
    default void log(String message) {
        System.out.println(message);
    }
}
