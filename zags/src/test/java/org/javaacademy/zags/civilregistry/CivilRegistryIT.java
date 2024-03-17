package org.javaacademy.zags.civilregistry;

import org.javaacademy.zags.citizen.Citizen;
import org.javaacademy.zags.citizen.FamilyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CivilRegistryIT {

    @Test
    void registerMarriageAndDivorceSuccess() {
        CivilRegistry civilRegistry = new CivilRegistry("test");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Lena", "Ivanova", "Ivanovna", false);
        assertDoesNotThrow(() -> civilRegistry.registerMarriage(man, woman, LocalDate.now()));
        Assertions.assertEquals(FamilyStatus.MARRIED, man.getFamilyStatus());
        Assertions.assertEquals(FamilyStatus.MARRIED, woman.getFamilyStatus());
        civilRegistry.registerDivorce(man, woman, LocalDate.now());
        Assertions.assertEquals(FamilyStatus.DIVORCED, man.getFamilyStatus());
        Assertions.assertEquals(FamilyStatus.DIVORCED, woman.getFamilyStatus());

    }

    @Test
    void registerMarriageSameSexFail() {
        CivilRegistry civilRegistry = new CivilRegistry("test");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Lena", "Ivanova", "Ivanovna", true);
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistry.registerMarriage(man, woman, LocalDate.now()));
    }

    @Test
    void registerDivorceNotCoupleFail() {
        CivilRegistry civilRegistry = new CivilRegistry("test");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Lena", "Ivanova", "Ivanovna", false);
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistry.registerDivorce(man, woman, LocalDate.now()));
    }

    @Test
    void registerBirthSuccess() {
        CivilRegistry civilRegistry = new CivilRegistry("test");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Lena", "Ivanova", "Ivanovna", false);
        Citizen child = man.birthNewHuman("Petr", "Ivanovich", true, woman);
        assertDoesNotThrow(() -> civilRegistry.registerBirth(man, woman, child, LocalDate.now()));
    }

    @Test
    void birthSameSexParentsFail() {
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Assertions.assertThrows(RuntimeException.class,
                () -> man.birthNewHuman("Petr", "Ivanovich", true, woman));
    }

    @Test
    void printInfoSuccess() {
        CivilRegistry civilRegistry = new CivilRegistry("test");
        Citizen man = new Citizen("Ivan", "Ivanov", "Ivanovich", true);
        Citizen woman = new Citizen("Lena", "Ivanova", "Ivanovna", false);
        civilRegistry.registerMarriage(man, woman, LocalDate.now());
        assertDoesNotThrow(civilRegistry::printInfo);
    }
}
