package com.bl.cricketanalyzer.exception;

public class CricketAnalyserException extends Exception {
    public ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }
    public enum ExceptionType {
         NO_CRICKET_DATA, WRONG_CLASS, CRICKET_FILE_PROBLEM,NO_SUCH_CLASS
    }
} 