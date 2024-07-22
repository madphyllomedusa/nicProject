package ru.nicetu.Project;

import java.io.IOException;


import ru.nicetu.Project.trajectory.Trajectory;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/matthewphyllomedusa/Downloads/traject1.txt";
        double searchTime = 10.0;

        try {
            Trajectory trajectory = Trajectory.fromFile(filePath);
            System.out.println("Все точки траектории:");
            trajectory.printAll();
            System.out.println("_________________\n");
            System.out.println(trajectory.toString());
            System.out.println(trajectory.getFileName());
            System.out.println(trajectory.getFileFormat());


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
