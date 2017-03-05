package com.imr.ApiSnowPiecer1;
import java.util.Properties;

/**
 * Created by imranp on 3/5/2017.
 */
public class FeatureGates {

    private static FeatureGates featureGates=new FeatureGates();

    Properties property= new Properties();

    public  boolean enableSlackNotification;
    public boolean  enableMailNotification;
    public boolean notifyBrokenURLinSlack;
    public boolean notifyBadURLsInSlack;
    public boolean notifyBrokenURLsinMail;
    public boolean notifyBadURLsInMail;

    public String enableAndroidTest;
    public String enableIosTest;
    public String enableFeedObjectTest;

    public boolean enableNavTest;
    public boolean enableSlideShowTest;
    public boolean enableHotlistTest;
    public boolean enableFeedTest;
    public boolean enableCortexTest;

    boolean enableDBUpdate;



    private FeatureGates()
    {
        Configuration config=new Configuration();
        property=config.getPropertyFile();

        enableAndroidTest=property.getProperty("Android.Test.Enable");
        enableIosTest=property.getProperty("Ios.Test.Enable");
        enableFeedObjectTest=property.getProperty("FeedObject.Test.Enable");

        enableSlackNotification=Boolean.parseBoolean(property.getProperty("SlackNotfication.Enalble"));
                enableMailNotification=Boolean.parseBoolean(property.getProperty("MailNotification.Enable"));
        notifyBrokenURLinSlack=Boolean.parseBoolean(property.getProperty("Slack.sendBrokenURLs"));
        notifyBadURLsInSlack=Boolean.parseBoolean(property.getProperty("Slack.sendBadURLs"));
        notifyBrokenURLsinMail = Boolean.parseBoolean(property.getProperty("Mail.sendBrokenURLs"));
        notifyBadURLsInMail=Boolean.parseBoolean(property.getProperty("Mail.sendBadURLs"));
        enableNavTest=Boolean.parseBoolean(property.getProperty("Nav.Test.Enable"));
        enableSlideShowTest=Boolean.parseBoolean(property.getProperty("SlideShow.Test.Enable"));
        enableHotlistTest=Boolean.parseBoolean(property.getProperty("Hotlist.Test.Enable"));
        enableFeedTest=Boolean.parseBoolean(property.getProperty("Feed.Test.Enable"));
        enableCortexTest=Boolean.parseBoolean(property.getProperty("Cortex.Test.Enable"));
        enableDBUpdate=Boolean.parseBoolean(property.getProperty("DB.Update.Enable"));


    }

    public static FeatureGates getInstance(){
        return featureGates;
    }





}
