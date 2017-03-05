package com.imr.ApiSnowPiecer1;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by imranp on 3/5/2017.
 */
public class TEST {
    public static void main(String args[]){
        ArrayList<String> Base=new ArrayList<String>();
        ArrayList<String> Base2=new ArrayList<String>();
        ArrayList<String> Base3=new ArrayList<String>();

        HashSet<String> delta= new HashSet<String>();
        for(int i=0;i<10;i++){
            Base.add(Integer.toString(i));
        }
        for(int i=0;i<10;i+=2){
            delta.add(Integer.toString(i));
        }

        System.out.println("DELTA BEFORE -ION");

        System.out.println(delta);

        System.out.println("BASE BEFORE -ION");
        for(int i=0;i<Base.size();i++){
            System.out.println(Base.get(i));
        }

        Base3=(ArrayList<String>) CollectionUtils.subtract(Base,delta);

        System.out.println("BASE AFTER -ION");
        for(int i=0;i<Base3.size();i++){
            System.out.println(Base3.get(i));
        }
    }
}
