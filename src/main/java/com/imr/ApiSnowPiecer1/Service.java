package com.imr.ApiSnowPiecer1;

import com.imr.ApiSnowPiecer.Configs.APINAME;
import com.imr.ApiSnowPiecer.Configs.SERVICENAME;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by imranp on 12/31/2016.
 */
public class Service {

    String BaseURL;
    String Port;
    String Path;
    String URL;
    String Payload;
    String RequestType;
    boolean PayloadRequired;
    boolean QueryParamRequired;
    Configuration config= new Configuration();


    public Service(SERVICENAME service, com.imr.ApiSnowPiecer.Configs.APINAME api){
        API details=config.LoadServiceDetails(service,api);
        if(!details.Port.equals(""))
        {
            BaseURL=details.Host+":"+details.Port;
        }
        else
        {
            BaseURL=details.Host;
        }

        Port=details.Port;
        Path=details.Path;
        URL=getCompleteURL(BaseURL,Path);
        RequestType=details.RequestType;
        PayloadRequired=details.PayloadRequired;
        QueryParamRequired=details.QueryParamRequired;
    }


    public Service(SERVICENAME service, APINAME api, String[] payload)
    {
        API details=config.LoadServiceDetails(service,api);
        if(!details.Port.equals(null))
        {
            BaseURL=details.Host+":"+details.Port;
        }
        else
        {
            BaseURL=details.Host;
        }

        Port=details.Port;
        Path=details.Path;
        URL=getCompleteURL(BaseURL,Path);
        RequestType=details.RequestType;
        PayloadRequired=details.PayloadRequired;
        QueryParamRequired=details.QueryParamRequired;
        Payload=PreaparePayload(details.Payload.payload);
        //printAPIDetails();
    }

    public Service(com.imr.ApiSnowPiecer.Configs.APINAME api)
    {
        //LoadAPIDetails(api.config);
        API details=config.LoadAPIDetails(api.name().toString());
        BaseURL=config.BaseURL;
        Path=details.Path;
        URL=getCompleteURL(BaseURL,Path);
        RequestType=details.RequestType;
        PayloadRequired=details.PayloadRequired;
        QueryParamRequired=details.QueryParamRequired;
        //printAPIDetails();

    }

    public Service(com.imr.ApiSnowPiecer.Configs.APINAME api, String[] payload){

        //LoadAPIDetails(api,config,payload);

        API details=config.LoadAPIDetails(api.name().toString());
        BaseURL=config.BaseURL;
        Path=details.Path;
        URL=getCompleteURL(BaseURL,Path);
        RequestType=details.RequestType;
        PayloadRequired=details.PayloadRequired;
        QueryParamRequired=details.QueryParamRequired;
        Payload=PreaparePayload(details.Payload.payload);
        //printAPIDetails();
    }

    public void LoadAPIDetails(com.imr.ApiSnowPiecer.Configs.APINAME api, Configuration config)
    {
        API details=config.LoadAPIDetails(api.name().toString());
        BaseURL=config.BaseURL;
        Path=details.Path;
        URL=getCompleteURL(BaseURL,Path);
        RequestType=details.RequestType;
        PayloadRequired=details.PayloadRequired;
        QueryParamRequired=details.QueryParamRequired;
        Payload=PreaparePayload(details.Payload.payload);
    }

    public void LoadServiceDetails(SERVICENAME service, com.imr.ApiSnowPiecer.Configs.APINAME api, Configuration config)
    {

        API details=config.LoadAPIDetails(api.name().toString());

    }

    public void LoadServiceDetails(SERVICENAME service, APINAME api, Configuration config, String[] payload)
    {
        API details=config.LoadAPIDetails(api.name().toString());
    }

    public void printAPIDetails()
    {
        System.out.println("BASEURL  : "+BaseURL);
        System.out.println("PATH   : "+Path);
        System.out.println("URL   : "+URL);
        System.out.println("REQUEST  : "+RequestType);
        System.out.println("PAYLOADREQUIRED  : "+PayloadRequired);
        System.out.println("QUERYPARAMREQUIRED  : "+QueryParamRequired);
        System.out.println("PAYLOAD   : "+Payload);
    }

    public String getCompleteURL(String BaseURL, String Path){
        return BaseURL+"/"+Path;
    }

    public void setServiceURL(String newURL)
    {
        URL=newURL;
    }
    public String getServiceURL()
    {
        return URL;
    }

    public String PreaparePayload(String PayloadTemplate, String[] payloadParams)
    {
        Map<String,String> valuesMap=new HashMap<String,String>();
        int paramnumber=0;
        for (String param:payloadParams) {
            valuesMap.put(Integer.toString(paramnumber),param);
            paramnumber++;

        }
        String templateString=PayloadTemplate;
        StrSubstitutor sub=new StrSubstitutor(valuesMap);
        String resolvedString=sub.replace(templateString);
        return resolvedString;

    }
}
