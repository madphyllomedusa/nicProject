package ru.nicetu.Project.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceTrajectory {
    ArrayList<Double> getPointByTime(double time);
    List<ArrayList<Double>> getAll();
    void printAll();
}
