package com.universitysystem.util;

public enum FileType {
    UNIVERSITY_DATA("university_data.txt");

    private final String fileName;

    FileType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}