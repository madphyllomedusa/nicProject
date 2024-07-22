package ru.nicetu.Project;

import java.io.IOException;
import ru.nicetu.Project.trajectory.Trajectory;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/matthewphyllomedusa/Downloads/traject1.txt";

        try {
            Trajectory trajectory = Trajectory.fromFile(filePath);
            System.out.println("Все точки траектории:");
            trajectory.printAll();
            System.out.println("_________________\n");
            System.out.println(trajectory.toString());
            System.out.println(trajectory.getFileName());
            System.out.println(trajectory.getFileFormat());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
