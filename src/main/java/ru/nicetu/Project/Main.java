package ru.nicetu.Project;

import java.io.IOException;

import ru.nicetu.Project.dataClass.Point;
import ru.nicetu.Project.trajectory.TrajectoryInfo;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/matthewphyllomedusa/Downloads/traject1.txt";
        double searchTime = 10.0;

        try {
            TrajectoryInfo trajectory = TrajectoryInfo.fromFile(filePath);
            System.out.println(trajectory.toString());


            System.out.println("Все точки и их координаты:");
            for (Point point : trajectory.getTrajectory().getPoints()) {
                System.out.println(point);
            }

            if (trajectory.getPointBySecond(searchTime) != null) {
                System.out.println("Значение траектории в точке "+ searchTime +
                        ":\n" +trajectory.getPointBySecond(searchTime).toString());
            }
            else {
                System.out.println("Точка траектории в момент времени "
                        + searchTime + " не найдена.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
