package com.imr.ApiSnowPiecer1;

/**
 * Created by imranp on 12/31/2016.
 */
public class API {

    String Host;
    String Port;
    String Path;
    String Payload;
    String RequestType;
    boolean PayloadRequired;
    boolean QueryParamRequired;

    public API(String path, String payload, String requestType, boolean payloadRequired, boolean queryparamRequired){

     Path=path;
     Payload=payload;
     RequestType=requestType;
     PayloadRequired=payloadRequired;
     QueryParamRequired=queryparamRequired;

    }

    public API(String host, String port, String path, String payload, String requestType, boolean payloadRequired, boolean queryParamRequired){
        Host=host;
        Port=port;
        Path=path;
        Payload=payload;
        RequestType=requestType;
        PayloadRequired=payloadRequired;
        QueryParamRequired=queryParamRequired;
    }

    public API(String path, String requestType, boolean payloadRequired, boolean queryParamRequired){
        Path=path;
        RequestType=requestType;
        PayloadRequired=payloadRequired;
        QueryParamRequired=queryParamRequired;
    }

    public API(String host, String port, String path, String requestType, boolean payloadRequired, boolean queryParamRequired){
        Host=host;
        Port=port;
        Path=path;
        RequestType=requestType;
        PayloadRequired=payloadRequired;
        QueryParamRequired=queryParamRequired;
    }

}
