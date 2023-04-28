package com.xrkmed.accesspoint.exceptions;

public class InvalidArray extends Exception {
    
    public InvalidArray(){
        super("Invalid array");
    }

    public InvalidArray(String message){
        super(message);
    }
}
