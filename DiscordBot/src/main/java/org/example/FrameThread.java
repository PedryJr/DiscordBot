package org.example;

import javax.swing.*;
import java.util.ArrayList;

import static org.example.Run.api;
public class FrameThread implements Runnable{

    ArrayList<String> updatingImage = new ArrayList<>();

    @Override
    public void run() {

        updatingImage.add("bin/Assets/Background/1.jpg");

        for(int i = 1; i < 123; i++){

            if(i < 10){

                updatingImage.add("bin/Assets/Background/000" + i + ".png");

            }else if(i < 100){

                updatingImage.add("bin/Assets/Background/00" + i + ".png");

            }else{

                updatingImage.add("bin/Assets/Background/0" + i + ".png");

            }

        }

        ImageFrame frame = new ImageFrame(updatingImage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(490, 500);
        frame.setLocation(0, 0);

        while(true){

            try {
                Thread.sleep(40);
                frame.Update();
            } catch (InterruptedException e) {
                api.getChannelById(1095484188389543977L).get().asTextChannel().get().sendMessage(e.getMessage());
            }

        }

    }
}
