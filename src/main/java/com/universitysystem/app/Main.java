package com.universitysystem.app;

import com.universitysystem.model.University;
import com.universitysystem.service.DataPersistenceService;
import com.universitysystem.service.DataSeeder;
import com.universitysystem.service.MenuService;

public class Main {

    public static void main(String[] args) {

        DataPersistenceService dataPersistenceService = new DataPersistenceService();
        University university = dataPersistenceService.loadUniversity();

        if (university == null) {
            university = DataSeeder.createUniversity();
            dataPersistenceService.saveUniversity(university);
        }

        MenuService menuService = new MenuService(university);
        menuService.start();
    }
}