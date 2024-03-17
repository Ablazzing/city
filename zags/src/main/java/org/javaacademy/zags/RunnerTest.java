package org.javaacademy.example;

import org.javaacademy.example.citizen.Citizen;
import org.javaacademy.example.citizen.CitizenGenerator;
import org.javaacademy.example.civilregistry.CivilRegistry;

import java.time.LocalDate;

public class RunnerTest {
    public static void main(String[] args) {
        CivilRegistry civilRegistry = new CivilRegistry("TEST");
        Citizen man = CitizenGenerator.generateMan();
        Citizen women = CitizenGenerator.generateWomen();
        civilRegistry.registerMarriage(man, women, LocalDate.now());
        Citizen child = man.birthNewHuman("Yuri", "Yurivich", true, women);
        civilRegistry.registerBirth(child, man, women, LocalDate.now());
        civilRegistry.registerDivorce(man, women, LocalDate.now());
        civilRegistry.printInfo();
    }
}
