package com.universitysystem.util;

import java.util.List;

public interface IFileUtil {

    void createFile(String path, String nameFile);
    boolean existsFile(String path);
    void addLineToFile(String path, String line);
    void clearFile(String path);
    List<String> readAllLines(String path);
}