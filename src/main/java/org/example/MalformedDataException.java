package org.example;

import java.io.IOException;

public class MalformedDataException extends RuntimeException {

//
//
//    public MalformedDataException() {
//    }
//
//    public MalformedDataException(String message) {
//        super(message);
//    }
//
//    public MalformedDataException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public MalformedDataException(Throwable cause) {
//        super(cause);
//    }


    public MalformedDataException(String error) {
        super(error);
    }

    public MalformedDataException(IOException error) {
        super(error);
    }
}
