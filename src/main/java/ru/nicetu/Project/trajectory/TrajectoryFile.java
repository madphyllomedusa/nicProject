package ru.nicetu.Project.trajectory;

import ru.nicetu.Project.interfaces.InterfaceFile;

import java.nio.file.Paths;

public class TrajectoryFile implements InterfaceFile {
    private String path;
    private String format;
    private String content;

    public TrajectoryFile(String path, String format, String content) {
        this.path = path;
        this.format = format;
        this.content = content;
    }

    @Override
    public String getFormat() {
        int dotIndex = path.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : path.substring(dotIndex);
        System.out.println(extension);
        return extension;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getName() {
        return Paths.get(path).getFileName().toString();
    }

    @Override
    public String getData() {
        return content;
    }

    @Override
    public String toString() {
        return "TrajectoryFile{" +
                "path='" + path + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
