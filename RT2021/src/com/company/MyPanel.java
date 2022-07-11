package com.company;


import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    @Getter
    private final ArrayList<Tree> trees;

    public MyPanel() {
        trees = new ArrayList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Tree tree : trees) {
            g.setColor(tree.getC());
            g.fillOval(tree.getX(), tree.getY(), tree.getOvalWidth(), tree.getOvalHeight());
            g.fillRect(tree.getRectX(), tree.getRectY(), tree.getRectWidth(), tree.getRectHeight());
        }
    }
    public void addTree(Tree tree) {
        trees.add(tree);
    }
    public Tree getLastTree() {
        return trees.get(trees.size() - 1);
    }
}
