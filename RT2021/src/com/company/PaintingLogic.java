package com.company;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class PaintingLogic extends UniversalAdapter {
    private final MyPanel panel;
    private boolean draw;
    private int count;
    private final JLabel currentColor;
    private final ArrayList<Color> colors;
    private Tree treeToMove;
    int x, y;
    private int random;
    public PaintingLogic(JFrame frame) {
        this.panel = new MyPanel();
        frame.add("Center", panel);
        this.panel.addMouseListener(this);
        this.panel.addMouseMotionListener(this);
        JPanel menu = new JPanel(new GridLayout());
        JButton tree = new JButton("Draw");
        JButton drag = new JButton("Drag");
        JButton changeColor = new JButton("Change Color");
        currentColor = new JLabel();
        menu.add(tree);
        menu.add(drag);
        menu.add(changeColor);
        menu.add(currentColor);
        tree.addActionListener(this);
        drag.addActionListener(this);
        changeColor.addActionListener(this);
        frame.add("South", menu);
        draw = false;
        count = 0;
        colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.ORANGE);
        colors.add(Color.RED);
        currentColor.setBackground(Color.CYAN);
        currentColor.setOpaque(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xx = e.getX();
        int yy = e.getY();
        if (draw) {
            panel.addTree(new Tree(xx, yy, colors.get(count%3)));
        }
        if(!draw) {
            if( checkTree(xx, yy) != null){
                treeToMove = checkTree(xx, yy);
                x = e.getX() - treeToMove.getX();
                y = e.getY() - treeToMove.getY();
            }
        }
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX();
        int dy = e.getY();
        if (draw) {
            drawNewTree(dx, dy);
        }
        if (!draw && treeToMove != null) {
            treeToMove.setX(dx - x);
            treeToMove.setY(dy -y);
        }
        panel.repaint();
    }
    private Tree checkTree(int dx, int dy) {
        ArrayList<Tree> result = new ArrayList<>(panel.getTrees());
        Collections.reverse(result);
        for (Tree tree : result) {
            Rectangle r = new Rectangle(tree.getX(), tree.getY(), tree.getOvalWidth(), (tree.getOvalHeight()/2)*3);
            if(r.contains(dx, dy)){
                return tree;
            }
        }
        return null;
    }
    private void drawNewTree(int dx, int dy) {
        Tree curTree = panel.getLastTree();
        int startX = curTree.getStartX();
        int startY = curTree.getStartY();
        if (dx > startX && dy > startY) {
            curTree.setY(startY);
            curTree.setX(startX);
            curTree.setAllDimensions(dx, dy);
        } else if (dx < startX && dy < startY) {
            curTree.setY(dy);
            curTree.setX(dx);
            curTree.setAllDimensions(dx, dy);
        } else if (dx < startX && dy > startY) {
            curTree.setY(startY);
            curTree.setX(dx);
            curTree.setAllDimensions(dx, dy);
        } else if (dx > startX && dy < startY) {
            curTree.setY(dy);
            curTree.setX(startX);
            curTree.setAllDimensions(dx, dy);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("random released " + random);
        treeToMove = null;
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Draw":
                draw = true;
                currentColor.setText("Draw");
                break;
            case "Drag":
                draw = false;
                currentColor.setText("Drag");
                break;
            case "Change Color":
                count++;
                currentColor.setBackground(colors.get(count%3));
                break;
        }
    }
}
