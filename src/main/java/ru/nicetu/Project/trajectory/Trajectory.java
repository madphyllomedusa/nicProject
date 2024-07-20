package ru.nicetu.Project.trajectory;

import ru.nicetu.Project.interfaces.InterfaceTrajectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Trajectory implements InterfaceTrajectory {
    private List<ArrayList<Double>> points;
    private TrajectoryFile fileInfo;

    public Trajectory(List<ArrayList<Double>> points, TrajectoryFile fileInfo) {
        this.points = points;
        this.fileInfo = fileInfo;
    }

    @Override
    public ArrayList<Double> getPointByTime(double time) {
        for (ArrayList<Double> point : points) {
            if (time == point.get(0)) {
                return point;
            }
        }
        return null;
    }

    @Override
    public List<ArrayList<Double>> getAll() {
        return points;
    }

    @Override
    public void printAll() {
        System.out.println("Trajectory: " + fileInfo);
        for (ArrayList<Double> point : points) {
            System.out.println(point);
        }
    }
    @Override
    public String toString(){
        return "Trajectory{" +
                "points=" + points.size() + " points" +
                ", fileInfo=" + fileInfo +
                '}';
    }

//    public static Trajectory fromFile(String filePath) throws IOException {
//        //todo
//        List<ArrayList<Double>> points = new ArrayList<>();
//        return new Trajectory(points);
//    }
}
