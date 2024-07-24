package ru.nicetu.Project.trajectory;

import ru.nicetu.Project.dataClass.Trajectory;

import java.util.List;

public interface InterfaceTInfo {
    public List<Double> getPointBySecond(double second);
    public Trajectory getTrajectory();
    public String getFilePath();
    public String getFileName();
    public String getFileFormat();
}
