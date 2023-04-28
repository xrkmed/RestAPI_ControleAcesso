package com.xrkmed.accesspoint.exceptions;

public class ExtraTimeDescanso extends Exception {
        public ExtraTimeDescanso(){
            super("O funcionario descansou por mais de 1 hora e 10 minutos.");
        }
    
        public ExtraTimeDescanso(String message){
            super(message);
        }
    
}
