package ru.nicetu.Project.trajectory;


import java.nio.file.NoSuchFileException;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Trajectory {
    private List<ArrayList<Double>> points;
    private final String path;

    private static Logger logger = Logger.getLogger(Trajectory.class.getName());

    public Trajectory(List<ArrayList<Double>> points, String path) {
        this.points = points;
        this.path = path;
    }


    public ArrayList<Double> getPointBySecond(double second) {
        for (ArrayList<Double> point : points) {
            if (second == point.get(0)) {
                return point;
            }
        }
        return null;
    }

    public List<ArrayList<Double>> getAll() {
        return points;
    }

    public void printAll() {
        for (ArrayList<Double> point : points) {
            //System.out.println(point);
            logger.info("print some point:" + point);
        }
    }

     public static Trajectory fromFile(String filePath) throws IOException {
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


            return new Trajectory(points, filePath);
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + filePath);
            throw e;
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + filePath);
            throw e;
        }
    }

    public String getFileName() {
        return Paths.get(path)
                .getFileName()
                .toString();
    }
    public String getFileFormat(){
        int dotIndex = path.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : path.substring(dotIndex);
        return extension;
    }
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Trajectory{\n" +
                "pointsCount=" + points.size() +"\n"+
                "File path = " + getPath() + "\n"+
                "File name = " + getFileName() +"\n"+
                "File format = " + getFileFormat() +"\n"+
                '}';
    }
}

