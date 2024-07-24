package ru.nicetu.Project.dataClass;

import java.util.List;

public class Trajectory {
    private List<Point> points;

    public Trajectory(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }
    public int getPointsCount() {
        return points.size();
    }

}