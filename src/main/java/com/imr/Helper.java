package com.imr.Tests;

import com.imr.ApiSnowPiecer.Configs.APINAME;
import com.imr.ApiSnowPiecer.Configs.SERVICENAME;
import com.imr.ApiSnowPiecer1.*;

import java.util.ArrayList;

/**
 * Created by imranp on 3/5/2017.
 */
public class Helper {

    Utilities util=new Utilities();
    ArrayList<String> CompleteList=new ArrayList<String>();
    FeatureGates featureGates=FeatureGates.getInstance();
    DataVersions versions=new DataVersions();
    Request request;
    Service service;
    String response;

    public Request GenerateRequest(String QueryParam, APINAME apiname){
        Service service= new Service(apiname);
        service.setServiceURL(util.preparepareameterizedURL(service.getServiceURL(),QueryParam);
        System.out.println("SERVICE URL: "+service.getServiceURL());
        return new Request(service);
    }

    public Request SendSlackMessage(String [] params){
        Service service=new Service(SERVICENAME.SLACK, APINAME.NOTIFYINSLACK,params);
        System.out.println("SERVICE URL : "+service.getServiceURL());
        return new Request(service);

    }

    public Request SignIn(String username,String password){
        Service service=new Service(SERVICENAME.PRODUCTIONHTTPS,APINAME.USERSIGNIN,new String[]{username,password});
        System.out.println("SERVICE URL : "+service.getServiceURL());
        return new Request(service);
    }

    public Request GetExcludeList()
    {
        Service service= new Service(SERVICENAME.PRODUCTIONHTTPS,APINAME.GETBYPASSURLS);
        System.out.println("SERVICE URL: "+service.getServiceURL());
        return new Request(service);

    }

    public ArrayList<String> prepareLinks(TESTPLATFORM platform)
    {
        GenerateFeedLinks(platform);
        GenerateNavLinks();
        GenerateHotListLinks(platform);
        GenerateSlideShowLinks();
        return completeList;
    }

}
