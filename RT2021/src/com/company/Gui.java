package com.company;

import javax.swing.*;
import java.awt.*;

public class Gui {

    public Gui() {
        JFrame frame = new JFrame("Rectangles");

        new PaintingLogic(frame);
        frame.setMinimumSize(new Dimension(700,700));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
