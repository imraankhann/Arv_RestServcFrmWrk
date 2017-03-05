package com.imr.ApiSnowPiecer1;

import org.apache.commons.digester.RegexMatcher;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by imranp on 1/15/2017.
 */
public class Crawler {

    private CrawlerLinks cl;
    private String XID,AT;
    private HashMap<String, String> header=new HashMap<String, String>();
    private ArrayList<String> passedURLs=new ArrayList<String>();
    private ArrayList<String> failedURLs=new ArrayList<String>();

    private HashMap<String, Integer> badURLs= new HashMap<String, Integer>();
    private HashMap<String,Integer> brokenURLs=new HashMap<String, Integer>();

    private HashMap<String,String> failedLayoutURLs=new HashMap<String, String>();
    private ArrayList<String>allURLs=new ArrayList<String>();
    private int TotalCount=0;
    private int SingleUrlsCount=0;
    private ArrayList<String> breadCrumbs=new ArrayList<String>();
    HashSet<String> FinalList=new HashSet<String>();
    HashSet<String> executedURLs=new HashSet<String>();
    HashSet<String> availableURLs= new HashSet<String>();


    HashMap<String,String> FinalLayoutList=new HashMap<String, String>();

    Notify notifier=new Notify();
    private LinksGenerator lg;
    private RegexMatcher regexMatcher=RegexMatcher.getInstance();

    public Crawler(CrawlerLinks CL, LinksGenerator LG)
    {
        setXID("snowpiecer@gmail.com","snowpiecer");
        this.lg=LG;
        TotalCount=CL.LayoutLinks.size()+CL.singleLinks.size();
        SingleURLsCount=CL.SingleLinks.size();
        prepareLayoutLinks(CL.LayoutLinks);

    }


}
