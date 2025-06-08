package com.theinvestorthing.bff.wallet.commons.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
