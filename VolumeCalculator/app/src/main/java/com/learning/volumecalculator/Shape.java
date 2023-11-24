package com.learning.volumecalculator;

public class Shape {
    int shape;
    String shapeName;

    public Shape(int shape, String shapeName){
        this.shape = shape;
        this.shapeName = shapeName;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public int getShape() {
        return shape;
    }

    public String getShapeName() {
        return shapeName;
    }
}