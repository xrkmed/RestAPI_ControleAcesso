package com.xrkmed.accesspoint.exceptions;

public class InsuffTimeWorked extends Exception {
        
        public InsuffTimeWorked(){
            super("Insufficient time worked");
        }
    
        public InsuffTimeWorked(String message){
            super(message);
        }
    
}
