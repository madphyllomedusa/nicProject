package ru.nicetu.Project.trajectory;


import java.nio.file.NoSuchFileException;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Trajectory {
    private List<ArrayList<Double>> points;
    private TrajectoryFile fileInfo;

    public Trajectory(List<ArrayList<Double>> points, TrajectoryFile fileInfo) {
        this.points = points;
        this.fileInfo = fileInfo;
    }


    public ArrayList<Double> getPointByTime(double time) {
        for (ArrayList<Double> point : points) {
            if (time == point.get(0)) {
                return point;
            }
        }
        return null;
    }

    public List<ArrayList<Double>> getAll() {
        return points;
    }

    public void printAll() {
        System.out.println("Trajectory: " + fileInfo);
        for (ArrayList<Double> point : points) {
            System.out.println(point);
        }
    }

    public String toString() {
        return "Trajectory{" +
                "pointsCount=" + points.size() +
                ", fileInfo=" + fileInfo +
                '}';
    }

     public static Trajectory fromFile(String filePath, String fileFormat) throws IOException {
        try {
            if (!Files.exists(Paths.get(filePath))) {
                throw new NoSuchFileException("File not found: " + filePath);
            }

            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.trim().split("\\R+");
            List<ArrayList<Double>> points = new ArrayList<>();

            for (String line : lines) {
                String[] data = line.trim().split("\\s+");
                ArrayList<Double> point = new ArrayList<>();
                for (String value : data) {
                    point.add(Double.parseDouble(value));
                }
                points.add(point);
            }

            TrajectoryFile fileInfo = new TrajectoryFile(filePath, content);
            return new Trajectory(points, fileInfo);
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + filePath);
            throw e;
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + filePath);
            throw e;
        }
    }

    public String getFileName() {
        String fileName = fileInfo.getName();
        return fileName;
    }
    public String getFileFormat(){
        String fileFormat = fileInfo.getFormat();
        return fileFormat;
    }
}

