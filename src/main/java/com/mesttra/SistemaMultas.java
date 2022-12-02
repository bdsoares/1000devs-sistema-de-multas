package com.mesttra;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SistemaMultas {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("multas");
        factory.close();
    }
}
