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
    private Path trajectoryPath;

    public TrajectoryInfo(Trajectory trajectory, Path trajectoryPath) {
        this.trajectory = trajectory;
        this.trajectoryPath = trajectoryPath;
    }


    public static TrajectoryInfo fromFile(String filePath) throws IOException {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(Paths.get(filePath))) {
                throw new NoSuchFileException("File not found: " + filePath);
            }

            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.trim().split("\\R+");

            List<Point> trajectory = new ArrayList<>();

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
        } catch (NoSuchFileException e) {
            logger.info("File not found: " + filePath);
            throw e;
        } catch (IOException e) {
             logger.info("An error occurred while reading the file: " + filePath);
            throw e;
        }
    }

    @Override
    public List<Double> getPointBySecond(double second) {
        try {
            for (Point point : trajectory.getPoints()) {
                if (second == point.getPoint().get(0)) {
                    return point.getPoint();
                }
            }
        }
        catch (Exception e) {
            logger.info("Something went wrong when getting the points by second: " + second);
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
     public String toString() {
        return "Trajectory{\n" +
                "pointsCount = " + trajectory.getPointsCount() +"\n"+
                "File path = " + getFilePath() + "\n"+
                "File name = " + getFileName() +"\n"+
                "File format = " + getFileFormat() +"\n"+
                '}';
    }
}
