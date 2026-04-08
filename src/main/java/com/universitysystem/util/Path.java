package com.universitysystem.util;

public enum Path {
    DIRECTORY ("C:\\JAVA_CLASS");

    private final String pathDirectory;

    Path(String pathDirectory) {
        this.pathDirectory = pathDirectory;
    }

    public String getPathDirectory(){
        return pathDirectory;
    }
}
