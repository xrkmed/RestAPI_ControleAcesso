package com.xrkmed.accesspoint.models;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = WorkerNotFoundResponse.class)
public class WorkerNotFoundResponse extends WorkerEntity {

    private static final long serialVersionUID = 1L;

    private String message;

    public WorkerNotFoundResponse() {
    }

    public WorkerNotFoundResponse(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }
    
}
