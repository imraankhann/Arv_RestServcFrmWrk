package com.imr.ApiSnowPiecer1;

import org.apache.commons.lang.text.StrSubstitutor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by imranp on 1/15/2017.
 */
public class Utilities {

    public String preparepareameterizedURL(String URL, String StringParameters){
        Map<String,String> valuesMap=new HashMap<String,String>();
        int paramnumber=0;
        valuesMap.put(Integer.toString(paramnumber),encodeParams(StringParameters));
        String templateString=URL;
        StrSubstitutor sub=new StrSubstitutor(valuesMap);
        String resolvedString=sub.replace(templateString);
        return resolvedString;


    }


    public String prepareparameterizedURL(String URL, String[] StringParameters) {
        Map<String, String> valuesMap = new HashMap<String, String>();
        int paramnumber = 0;
        for (String param : StringParameters) {
            valuesMap.put(Integer.toString(paramnumber), encodeParams(param));
            paramnumber++;
        }
        String templateString = URL;
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        return resolvedString;


    }

    public String encodeParams(String param)
    {
        try
        {
            return URLEncoder.encode(param,"UTF-8");
        }catch(UnsupportedEncodingException e)
        {
            System.out.println("URL ENCODING ERROR @ "+param);
            return param;
        }


        public String getListAsString(ArrayList<String> List)
    {
        String result=null
                return result;

    }
    }
}
