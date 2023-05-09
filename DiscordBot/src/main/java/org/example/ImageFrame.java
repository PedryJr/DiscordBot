package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
class ImageFrame extends JFrame {

    public int X;
    public int Y;

    private int imgN = 0;
    ArrayList<String> imagePaths;
    public ImageFrame(ArrayList<String> imagePaths) {

        this.imagePaths = imagePaths;

        setTitle("Discord Bot");
        setSize(100, 100);

        ImageComponent component = new ImageComponent("bin/Assets/icons/icon.jpg");
        add(component);
        getContentPane().validate();
        getContentPane().repaint();

        try {

            Image image = ImageIO.read(new File("bin/Assets/icons/icon.jpg").getAbsoluteFile());

            setIconImage(image);

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        } catch (IOException e) {

            System.console().writer().println(e.getMessage());

            throw new RuntimeException(e);

        }

    }

    boolean updown = true;
    public void Update(){

        if(imgN > imagePaths.size()-2){

            updown = false;

        }
        if(imgN < 2){

            updown = true;

        }

        if(updown){

            imgN++;

        }else{

            imgN--;

        }


        ImageComponent component = new ImageComponent(imagePaths.get(imgN));
        getContentPane().removeAll();
        add(component);
        getContentPane().validate();
        getContentPane().repaint();

    }

}
