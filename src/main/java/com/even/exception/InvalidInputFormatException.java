package com.even.exception;

public class InvalidInputFormatException extends Exception{
    public final static int INVALID_CHARACTER = 1;
    public final static int INVALID_LENGTH = 2;

    int errorCode;
    public InvalidInputFormatException( int errorCode ){
        this.errorCode = errorCode;
    }

    @Override
    public String toString(){
        switch( errorCode ){
            case INVALID_CHARACTER : return "Input contains invalid character, please check.";
            case INVALID_LENGTH : return "Input contains invalid length.";
            default: return "Invalid input format";
        }
    }
}
