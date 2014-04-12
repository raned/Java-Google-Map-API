package com.map.parser;

import java.util.*;

import com.map.parser.entity.LocationInfo;

/**
 * Write a description of class JsonParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JsonParser
{

    HashMap<String,String> map=new HashMap<String,String>();
    public JsonParser(String json)
    {
        parse(json);
       
    }

    public String parse(String json){
        String newval;
        String[] jsonarr=json.split(":",2);
        for(int i=0; i < jsonarr.length-1; i+=2){
            Object[] objs=jsonarr[i].split(" ");
            //map.put(jsonarr[i].replaceAll("\" \"|[\\[}{,\\]\"]",""),jsonarr[i+1]);
            newval=((String)objs[objs.length-1]).replaceAll("\"","");
            String values=jsonarr[i+1].substring(0,jsonarr[i+1].indexOf("}"))+" }";
            //values=values.replaceAll("\\s"," ");
            //System.out.println(values.substring(0,2).contains("{"));
            //  if(!values.substring(0,2).contains("{"))
            if(map.containsKey(newval))
                map.put(newval+"1",values);
            else
                map.put(newval+"0",values);
        }
        String v=jsonarr[jsonarr.length-1];
        if(v.contains(":"))
            parse(v);
        return v;
    }

    public int closed(String lookup,String value){
        ArrayList<String> lookups =  new  ArrayList<String> ();
        for(int i=0; i < value.length(); i++)
            if(lookup.equals(value.charAt(i)))
                return i;
        return -1;
    }

    ArrayList<LocationInfo> locatinfo = new ArrayList<LocationInfo>();
    public void display(){
        // 
       int i=0;
       //for(Object obj:map.keySet().toArray()){
        	locatinfo.add(new LocationInfo(map.get("status"+i).equals("OK")?true:false, map.get("location"+i), map.get("lng"+i), map.get("lat"+i),
        			         map.get("short_name"+i), map.get("types"+i), map.get("long_name"+i), map.get("formatted_address"+i)));
        	i++;
           // System.out.println(obj+":"+map.get(obj));
       //	}
    }

    public ArrayList<LocationInfo> getLocation(){
    	display();
    	return locatinfo;
    }
    
}
