package com.imr.ApiSnowPiecer1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by imranp on 3/5/2017.
 */
public class RegexMatcher {

    HashMap<String,String> patterns=new HashMap<String,String>();
    private static RegexMatcher regexMatcher=new RegexMatcher();

    private RegexMatcher()
    {
        patterns.put("myx","^(\\/shop\\/(?:([^\\/]+?)))\\/?$");
        patterns.put("youtube","\\/www.youtube.com\\/");
        patterns.put("app-referral","\\/app\\/referral");

    }

    public static RegexMatcher getInstance(){
        return regexMatcher;
    }

    public String getURLType(String URL){
        String type="search";
        for(Map.Entry<String,String> entry: patterns.entrySet()){
            if(URL.matches(entry.getValue())){
                type=entry.getKey();
                break;

            }


        }
        return type;
    }

    public HashMap<String,String> prepareURLTypes(ArrayList<String> URLs){
       HashMap<String,String> linksWithType=new HashMap<String,String>();
       for(int i=0;i<URLs.size();i++){
           String URLType=getURLType(URLs.get(i));
           linksWithType.put(URLs.get(i),URLType);

       }
       return linksWithType;

    }
    public HashMap<String,String> prepareURLTypes(HashSet<String>URLs){
        HashMap<String,String> linksWithType=new HashMap<String,String>();
        for(String url: URLs){
            String URLType=getURLType(url);
            linksWithType.put(url,URLType);
        }
        return linksWithType;
    }



}
