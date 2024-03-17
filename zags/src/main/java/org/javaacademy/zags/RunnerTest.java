package org.javaacademy.zags;

import org.javaacademy.zags.citizen.Citizen;
import org.javaacademy.zags.civilregistry.CivilRegistry;

import java.time.LocalDate;

public class RunnerTest {
    public static void main(String[] args) {
        CivilRegistry civilRegistry = new CivilRegistry("TEST");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen women = new Citizen("Lena", "Ivanova", "Ivanovna", false);
        civilRegistry.registerMarriage(man, women, LocalDate.now());
        Citizen child = man.birthNewHuman("Yuri", "Yurivich", true, women);
        civilRegistry.registerBirth(child, man, women, LocalDate.now());
        civilRegistry.registerDivorce(man, women, LocalDate.now());
        civilRegistry.printInfo();
    }
}
