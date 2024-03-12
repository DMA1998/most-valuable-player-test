package com.mykh.mvp.exception;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public class UnknownCsvType extends RuntimeException {

    public UnknownCsvType(String message) {
        super(message);
    }

}
