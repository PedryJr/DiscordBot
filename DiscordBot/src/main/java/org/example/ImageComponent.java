package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
class ImageComponent extends JComponent {
    private static final long serialVersionUID = 1L;
    private Image image;

    public ImageComponent(String path) {

        try {

            image = ImageIO.read(new File(path));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void paintComponent(Graphics g) {

        if (image == null) {

            return;

        }

        g.drawImage(image, 0, 0, this);

    }
}
