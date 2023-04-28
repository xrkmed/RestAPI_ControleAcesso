package com.xrkmed.accesspoint.models;

public class AccessPointCadError extends AccessPointCad {
    public AccessPointCadError() {
    }
    
    public AccessPointCadError(String message) {
        setReturnMessage(message);
    }
    
    public String getErrorMessage() {
        return getReturnMessage();
    }
    
}
