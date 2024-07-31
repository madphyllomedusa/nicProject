package ru.nicetu.Project.trajectory;

import ru.nicetu.Project.dataClass.Point;
import ru.nicetu.Project.dataClass.Trajectory;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrajectoryInfo implements InterfaceTInfo {
    private static Logger logger = Logger.getLogger(TrajectoryInfo.class.getName());


    private Trajectory trajectory;
    private final Path trajectoryPath;

    public TrajectoryInfo(Trajectory trajectory, Path trajectoryPath) {
        this.trajectory = trajectory;
        this.trajectoryPath = trajectoryPath;
    }


    public static TrajectoryInfo fromFile(String filePath) throws IOException {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                throw new NoSuchFileException("File not found: " + filePath);
            }

            String content = new String(Files.readAllBytes(path));
            String[] lines = content.trim().split("\\R+");

            List<Point> trajectory = new ArrayList<>();
            if (!content.isEmpty()) {
                for (String line : lines) {
                    String[] data = line.trim().split("\\s+");
                    List<Double> pointData = new ArrayList<>();
                    for (String value : data) {
                        pointData.add(Double.parseDouble(value));
                    }
                    Point trajectoryPoint = new Point(pointData);
                    trajectory.add(trajectoryPoint);
                    logger.info("add point: " + pointData);
                }

                Trajectory trajectoryObj = new Trajectory(trajectory);
                return new TrajectoryInfo(trajectoryObj, path);
            }
            else{
                logger.info("Wrong data format");
                return null;
            }
        } catch (NoSuchFileException e) {
            logger.info("File not found: " + filePath);
            throw e;
        } catch (IOException e) {
            logger.info("An error occurred while reading the file: " + filePath);
            throw e;
        }catch (NumberFormatException e){
            logger.info("An error data format.");
            throw e;
        }
    }

    @Override
    public List<Double> getPointBySecond(double second) {
        try {
            int i = 0;
            for (Point point : trajectory.getPoints()) {
                if (second == point.getPoint().get(0)) {
                    return point.getPoint();
                }else {
                   i++;
                }
            }
            if (i >= trajectory.getPointsCount()){
                throw new RuntimeException();
            }

        }
        catch (Exception e) {
            logger.warning("Something went wrong when getting the points by second: " + second);
        }
        return null;
    }


    @Override
    public Trajectory getTrajectory() {
        return trajectory;
    }

    @Override
    public String getFilePath() {
        return trajectoryPath
                .toString();
    }

    @Override
    public String getFileName() {
        return trajectoryPath
                .getFileName()
                .toString();
    }

    @Override
    public String getFileFormat(){
        int dotIndex = trajectoryPath
                .toString()
                .lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : trajectoryPath
                .toString().
                substring(dotIndex);
        return extension;
    }

    @Override
    public String getFileSize() throws IOException {
        try {
            long fileSize = Files.size(trajectoryPath);
            String size = String.valueOf(fileSize);
            return size;
        } catch (IOException e) {
           logger.warning("Error reading file size: " + e.getMessage());
           return null;
        }
    }


    @Override
     public String toString() {
        try {
            return "Trajectory{\n" +
                    "pointsCount = " + trajectory.getPointsCount() +"\n"+
                    "File path = " + getFilePath() + "\n"+
                    "File name = " + getFileName() +"\n"+
                    "File format = " + getFileFormat() +"\n"+
                    "File size = " + getFileSize() +" bytes"+ "\n"+
                    '}';
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
