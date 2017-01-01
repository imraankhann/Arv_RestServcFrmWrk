package com.imr.ApiSnowPiecer1;

import com.imr.ApiSnowPiecer.Configs.APINAME;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by imranp on 12/31/2016.
 */
public class Configuration {

    Properties prop=new Properties();
    InputStream IS=null;
    private String masterConfigFileName="MasterConfig.xml";
    private String propertiesFileName="./Data/TestSettings.properties";
    private String payloadFilePath="./Data/Payloads";
    private String MasterConfigPath;
    private String Environment;
    String BaseURL;
    HierarchicalConfiguration Config;
    FileInputStream FIS;
    XPathExpressionEngine E;
    private XMLConfiguration masterConfiguration;
    Service service;
    API api;


    public Configuration() {

        try {
            IS = new FileInputStream(propertiesFileName);
            prop.load(IS);
            LoadProperties();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error finding properties file @ path /Data");
            System.out.println("Loading Default settings");


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading protperties file @ path /Data");
            System.out.println("Loading Default settings");
            LoadDefaultProperties();
        } catch (Exception e) {
            System.out.println("Unkonwn Error ");
            System.out.println("Loading Default settings ");
            e.printStackTrace();
            LoadDefaultProperties();
        }

        try {
            masterConfiguration = new XMLConfiguration(getMasterConfigFilePath(MasterConfigPath));
            masterConfiguration.setExpressionEngine(E);

        } catch (Exception e) {
            System.out.println("error reading master configuration!");

        }
    }

        public void LoadDefaultProperties(){
            MasterConfigPath="./Data/Configuration";
            Environment="QA";
            Init();
    }

    public void Init(){
            setBaseURL(Environment);

    }

    public API LoadServiceDetails(SERVICENAME service, APINAME apiname) {

        String host = null;
        String port = null;

        List<HierarchicalConfiguration> serviceFields = masterConfiguration.configurationsAt("ServiceConfig.service.config");
        for (HierarchicalConfiguration sub : serviceFields) {
            if (sub.getString("[@name]").equals(service.name().toLowerCase())) {
                host = sub.getString("[@host]").toString();
                port = sub.getString("[@port]").toString();
            }
        }
        String path;
        String requesType;
        boolean payloadRequired;
        boolean queryParameterRequired;

        List<HierarchicalConfiguration> apiFields = masterConfiguration.configurationsAt("ServiceConfig.api.apiname");
        for (HierarchicalConfiguration sub : apiFields) {
            if (sub.getString("[@name]").equals(apiname.name().toLowerCase())) {
                path = sub.getString("[@path]").toString();
                requesType = sub.getString("[@requestType]").toString();
                payloadRequired = Boolean.parseBoolean(sub.getString("[@payloadRequired]").toString());
                queryParameterRequired = Boolean.parseBoolean(sub.getString("[@queryParameterRequired]").toString());
                if (payloadRequired) {
                    String payload = getPayloadAsString(apiname.name().toLowerCase());
                    api = new API(host, port, path, payload, requesType, payloadRequired, queryParameterRequired);

                } else {
                    api = new API(host, port, path, requesType, payloadRequired, queryParameterRequired);
                }
                break;
            }

        }
        return api;
    }



        public API LoadAPIDetails(String apiName)
        {
            String path;
            String requestType;
            boolean payloadRequired;
            boolean queryParameterRequired;
            List<HierarchicalConfiguration> fields=masterConfiguration.configurationsAt("ServiceConfig.api.apiname");
            for(HierarchicalConfiguration sub: fields)
            {
                if(sub.getString("[@path]").equals(apiName.toLowerCase())){
                    path=sub.getString("[@path]").toString();
                    requestType=sub.getString("#requestType").toString();
                    payloadRequired=Boolean.parseBoolean(sub.getString("[@payloadRequired]").toString());
                    if(payloadRequired)
                    {
                        String payload=getPayloadAsString(apiName);
                        api=new API(path,payload,requestType,payloadRequired,queryParameterRequired);
                    }
                    else
                    {
                        api=new API(path,requestType,payloadRequired,queryParameterRequired)
                    }
                    break;

                }
            }

            return api;
        }

        public String getPayloadAsString(String apiName){
            String content=null;
            String filePath=getPayloadFilePath(apiName);
            System.out.println("FILEPATH : "+filePath);
            try {
                BufferedReader reader= new BufferedReader(new FileReader(filePath));
                content= IOUtils.toString(reader);
                System.out.println("CONTENT : "+content);
            }catch (IOException e){
                System.out.println("Payload file not found...");
            }
            return content;
    }



        public String getPayloadFilePath(String apiName){
            return payloadFilePath+"/"+apiName.toLowerCase();
        }

        public String getMasterConfigFilePath(String configFolderPath){
         return configFolderPath+"/"+masterConfigFileName;
        }

        public void setBaseURL(String Environment){
          switch(Environment.toUpperCase()){
              case "QA":
              {
                  BaseURL="http://";
                  break;
              }

              case "PRODUCTION":
              {
                BaseURL=""   ;
                break;
              }


          }
        }

        public Properties getPropertyFile() {
            return prop;
        }

        public void LoadProperties()
        {
            MasterConfigPath="./Data/Configuration";
            Environment="QA";
            Init();
        }
}


}
