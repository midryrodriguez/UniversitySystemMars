package com.universitysystem.app;

import com.universitysystem.model.University;
import com.universitysystem.service.DataSeeder;
import com.universitysystem.service.MenuService;

public class Main {

    public static void main(String[] args) {

        University university = DataSeeder.createUniversity();

        MenuService menuService = new MenuService(university);
        menuService.start();
    }
}