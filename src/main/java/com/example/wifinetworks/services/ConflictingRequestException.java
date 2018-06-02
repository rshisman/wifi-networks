package com.example.wifinetworks.services;

public class ConflictingRequestException extends RuntimeException{

    public ConflictingRequestException(String message) {
        super(message);
    }
}
