package com.xrkmed.accesspoint.generals;

public class BuilderConfig {

    public static final String PACKAGE_FILIAL = "FLN";
    public static final String PACKAGE_PAIS = "BR";
    

    public static String getWorkerIdentificationFormat(long workerId){
        return PACKAGE_FILIAL + workerId + PACKAGE_PAIS;
    }
}
