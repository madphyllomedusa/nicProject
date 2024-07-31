package ru.nicetu.Project.trajectory;

import ru.nicetu.Project.dataClass.Trajectory;

import java.io.IOException;
import java.util.List;

public interface InterfaceTInfo {
    public List<Double> getPointBySecond(double second) throws IOException;
    public Trajectory getTrajectory();
    public String getFilePath();
    public String getFileName();
    public String getFileFormat();
    public String getFileSize() throws IOException;
}
