package com.universitysystem.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtil implements IFileUtil {

    @Override
    public void createFile(String path, String nameFile) {
        try {
            Path fullPath = Paths.get(path, nameFile);

            if (fullPath.getParent() != null) {
                Files.createDirectories(fullPath.getParent());
            }

            if (!Files.exists(fullPath)) {
                Files.createFile(fullPath);
                System.out.println("Archivo creado exitosamente en: " + fullPath);
            }

        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    @Override
    public boolean existsFile(String path) {
        Path filePath = Paths.get(path);
        return Files.exists(filePath);
    }

    @Override
    public void addLineToFile(String path, String line) {
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                System.err.println("Error: El archivo no existe en la ruta especificada.");
                return;
            }

            Files.writeString(
                    filePath,
                    line + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    @Override
    public void clearFile(String path) {
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                System.err.println("Error: El archivo no existe en la ruta especificada.");
                return;
            }

            Files.writeString(
                    filePath,
                    "",
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.err.println("Error al limpiar el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<String> readAllLines(String path) {
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            return Files.readAllLines(filePath);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}