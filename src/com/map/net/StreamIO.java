package com.map.net;


import java.net.*;
import java.io.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

import com.map.parser.JsonParser;
import com.map.parser.entity.LocationInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
public class StreamIO
{
    public static void main(String [] args)
    {
        //System.out.println( getJson(" http://maps.googleapis.com/maps/api/geocode/json?address=uwimona&sensor=false"));
    	JsonParser jsonparse=new JsonParser(getJson(" http://maps.googleapis.com/maps/api/geocode/json?address=japan&sensor=false"));
    	for(LocationInfo jsonp:jsonparse.getLocation()){
    		System.out.println(jsonp.getLongname());
    	}
            //System.out.println( getIp() );
            //String a="http://maps.googleapis.com/maps/api/staticmap?center=18.182400,-77.321800&zoom=12&size=400x400&maptype=hybrid&sensor=true&key=AIzaSyA-Y2n0AoEnL9nRQPTB9UdYG4Hp92eiDvw";
            //  a="http://maps.googleapis.com/maps/api/staticmap?center=New+York,NY&zoom=13&size=600x300&key=AIzaSyA-Y2n0AoEnL9nRQPTB9UdYG4Hp92eiDvw";
            // javax.swing.JOptionPane.showMessageDialog(null,new javax.swing.JLabel(getMap(a)));
    }

    public static String getIp(){
        try {
            String value="http://www.getip.com/";
            URL url = new URL(value);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while((line = in.readLine()) != null) {
                if(line.contains("Current IP:"))    
                    return  getLocation("http://www.geobytes.com/IpLocator.htm?GetLocation&IpAddress="+line.replaceAll("h1|[\\<>\\-\\=\\:\\//\\\"\",a-zA-Z\\s]",""));
                //System.out.println(line.replaceAll("h1|[\\<>\\-\\=\\:\\//\\\"\",a-zA-Z\\s]",""));//System.out.println(line.replaceAll("[a-zA-Z\\s]|(?<!-,)\\s",""));
            }
            //System.out.println(responseData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String  getJson(String urls){

        try{
            URL url = new URL(urls);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            String values="";
            while((line = in.readLine()) != null) {   
                values+=line;
            }
            return values;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLocation(String value){
        try {
            URL url = new URL(value);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            String values="";
            while((line = in.readLine()) != null) {      
                if(line.contains("ro-no_bots_pls10"))
                    values=line.replaceAll("<td align=\"right\"><input name=\"ro-no_bots_pls10\"|size=\"20\" readonly></td>|value=\"|\"","").trim()+",";

                if(line.contains("ro-no_bots_pls19"))
                    values+=line.replaceAll("<td align=\"right\"><input name=\"ro-no_bots_pls19\"|size=\"20\" readonly></td>|value=\"|\"","").trim();
            }
            return values;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ImageIcon getMap(String value){
        URL url;
        ImageIcon imgcom =null;
        try {
            //a="http://maps.googleapis.com/maps/api/staticmap?center=18.1824,-77.3218&zoom=8&size=400x400&maptype=roadmap&sensor=false";
            url = new URL(value);
            URLConnection conn = url.openConnection();
            BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(conn.getInputStream()));
            imgcom = new ImageIcon(img);
            // javax.swing.JOptionPane.showMessageDialog(null,new javax.swing.JLabel(new javax.swing.ImageIcon(img)));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgcom;
    }
}

