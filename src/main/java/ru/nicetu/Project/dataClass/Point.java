package ru.nicetu.Project.dataClass;

import java.util.List;

public class Point {
    private List<Double> point;

    public Point(List<Double> point) {
        this.point = point;
    }

    public List<Double> getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Point " +
                point;
    }
}