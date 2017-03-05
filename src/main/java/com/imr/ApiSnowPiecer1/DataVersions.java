package com.imr.ApiSnowPiecer1;
import java.util.Properties;

/**
 * Created by imranp on 3/5/2017.
 */
public class DataVersions {
    public String feedVersion;
    public String streamVersion;
    public String streamslideshowVersion;
    public String streamNavVersion;
    public String hotlistVersion;
    Properties property = new Properties();

    public DataVersions()
    {
        Configuration config=new Configuration();
        property=config.getPropertyFile();

        feedVersion=property.getProperty("Feed.Version");
        streamVersion=property.getProperty("Stream.Version");
        streamslideshowVersion=property.getProperty("Stream.SlideShow.Version");
        hotlistVersion=property.getProperty("hotlist.Version");


    }

}
