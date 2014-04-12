package com;

import javax.swing.*;

import com.map.MapPane;

import java.awt.*;
public class Frame
{

    public static  void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.add(new MapPane());
        frame.setPreferredSize(new Dimension(600,430));
       
        frame.pack();
         frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
