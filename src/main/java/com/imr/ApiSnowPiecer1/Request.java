package com.imr.ApiSnowPiecer1;



import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by imranp on 1/1/2017.
 */
public class Request {

    private Client client;
    private ClientConfig clientConfig;
    private WebTarget target;
    private Response response;
    private int responseCode;
    private String responseString;

    public Request(Service service){
        clientConfig=new ClientConfig();
        client= ClientBuilder.newClient(clientConfig);
        target=client.target(service.getServiceURL());

        switch(service.RequestType)
        {
            case "GET":
            {
                Invocation.Builder invocation=target.request();
                response=invocation.get();
                response.bufferEntity();
                responseString=response.readEntity(String.class);
                break;
            }

            case "POST":
            {
                response=target.request(MediaType.APPLICATION_JSON).post(Entity.json(service.Payload));
                response.bufferEntity();
                responseString=response.readEntity(String.class);
                break;
            }
        }


    }


    public Request(Service service,HashMap<String,String> headers)
    {
        clientConfig=new ClientConfig();
        client=ClientBuilder.newClient(clientConfig);
        target=client.target(service.getServiceURL());

        //switch(service.RequestType)
        switch (service.RequestType)
        {
            case "GET":
            {
                Invocation.Builder invocation = target.request();
                if (!(headers.size() == 0)) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        invocation.header(entry.getKey(), entry.getValue());
                    }
                }
                response = invocation.get();
                response.bufferEntity();
                responseString = response.readEntity(String.class);
                break;
        }

            case "POST":
            {
                Invocation.Builder invocation=target.request(MediaType.APPLICATION_JSON);
                if(!(headers.size()==0))
                {
                    for(Map.Entry<String,String> entry: headers.entrySet())
                    {
                        Object value=entry.getValue();
                        invocation.header(entry.getKey(),entry.getValue());
                    }

                }
                response=invocation.post(Entity.json(service.Payload));
                response.bufferEntity();
                responseString=response.readEntity(String.class);
                break;

        }

        }

    }

    public Request(String URL){
        clientConfig=new ClientConfig();
        client=ClientBuilder.newClient(clientConfig);
        target=client.target(URL);
        Invocation.Builder invocation=target.request();
        response=invocation.get();
    }

    public String getResponse(){
        response.bufferEntity();
        return response.readEntity(String.class);
    }

    public String getHeaderValue(String headerString)
    {
        return response.getHeaderString(headerString);

    }

    public int getResponseCode()
    {
        return response.getStatus();
    }


}
