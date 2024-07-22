package ru.nicetu.Project.trajectory;


import java.nio.file.Paths;
import java.nio.file.Path;

public class TrajectoryFile {
    private String path;
    private String content;

    public TrajectoryFile(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public String getFormat() {
        int dotIndex = path.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : path.substring(dotIndex);
        System.out.println(extension);
        return extension;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return Paths.get(path).getFileName().toString();
    }


    public String getData() {
        return content;
    }

}
