package com.company;

import lombok.Getter;
import lombok.Setter;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.Math;
import java.awt.*;

public class Tree {
    @Getter @Setter
    private int x, y;
    @Getter @Setter
    private  int startX, startY;
    @Getter @Setter
    private int rectWidth, rectHeight;
    @Getter @Setter
    private int ovalHeight, ovalWidth;
    @Getter @Setter
    private Color c;
    public Tree(int x, int y, Color c) {
        this.startX = x;
        this.startY = y;
        this.c = c;
        rectWidth = 1;
    }

    public void setAllDimensions(int dx, int dy) {
        setOvalWidth(Math.abs(dx - startX));
        setOvalHeight((Math.abs(dy - startY)/3 )* 2);
        setRectWidth(Math.abs(dx - startX) / 3);
        setRectHeight(ovalHeight);
    }
    public int getRectX() {
        return x + (getOvalWidth() / 3);
    }
    public int getRectY() {
        return y + (getOvalHeight()/2);
    }

}
