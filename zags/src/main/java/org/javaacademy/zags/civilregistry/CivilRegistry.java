package org.javaacademy.zags.civilregistry;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.zags.citizen.Citizen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.javaacademy.human.Human.checkHumansIsDifferentSex;
import static org.javaacademy.zags.citizen.FamilyStatus.DIVORCED;
import static org.javaacademy.zags.citizen.FamilyStatus.MARRIED;
import static org.javaacademy.zags.civilregistry.CitizenActionType.BIRTH;
import static org.javaacademy.zags.civilregistry.CitizenActionType.DIVORCE;
import static org.javaacademy.zags.civilregistry.CitizenActionType.MARRIAGE;

@RequiredArgsConstructor
public class CivilRegistry {
    private @NonNull String name;
    private final TreeMap<LocalDate, List<CitizenActionRecord>> actionRecordsByDate = new TreeMap<>();

    public void registerBirth(Citizen child,
                              Citizen father,
                              Citizen mother,
                              LocalDate registerDate) {
        Set<Citizen> citizenSet = Set.of(child, mother, father);
        CitizenActionRecord actionRecord = new CitizenActionRecord(BIRTH, registerDate, citizenSet);
        addActionForDate(registerDate, actionRecord);
    }

    public void registerMarriage(Citizen human, Citizen anotherHuman, LocalDate registerDate) {
        checkHumansIsDifferentSex(human, anotherHuman);
        human.setCoupleHuman(anotherHuman);
        anotherHuman.setCoupleHuman(human);
        human.setFamilyStatus(MARRIED);
        anotherHuman.setFamilyStatus(MARRIED);
        CitizenActionRecord actionRecord = new CitizenActionRecord(MARRIAGE, registerDate, Set.of(human, anotherHuman));
        addActionForDate(registerDate, actionRecord);
    }

    public void registerDivorce(Citizen human, Citizen anotherHuman, LocalDate registerDate) {
        checkCitizensIsCouple(human, anotherHuman);
        human.setCoupleHuman(null);
        anotherHuman.setCoupleHuman(null);
        human.setFamilyStatus(DIVORCED);
        anotherHuman.setFamilyStatus(DIVORCED);
        CitizenActionRecord actionRecord = new CitizenActionRecord(DIVORCE, registerDate, Set.of(human, anotherHuman));
        addActionForDate(registerDate, actionRecord);
    }

    public void printInfo() {
        for (Map.Entry<LocalDate, List<CitizenActionRecord>> entry : actionRecordsByDate.entrySet()) {
            long divorceCount = entry.getValue().stream().filter(e -> e.actionType() == DIVORCE).count();
            long marriageCount = entry.getValue().stream().filter(e -> e.actionType() == MARRIAGE).count();
            long birthCount = entry.getValue().stream().filter(e -> e.actionType() == BIRTH).count();
            String text = """
                    Статистика по ЗАГС: %s
                    Дата %s: количество свадеб - %s, количество разводов - %s, количество рождений - %s"
                    """.formatted(name, entry.getKey(), marriageCount, divorceCount, birthCount);
            System.out.println(text);
        }
    }

    private void checkCitizensIsCouple(Citizen human, Citizen anotherHuman) {
        checkHumansIsDifferentSex(human, anotherHuman);
        if (human.getCoupleHuman() == null || human.equals(anotherHuman)) {
            throw new RuntimeException("Humans not a couple");
        }
    }

    private void addActionForDate(LocalDate localDate, CitizenActionRecord action) {
        List<CitizenActionRecord> valueBefore = this.actionRecordsByDate.getOrDefault(localDate, new ArrayList<>());
        valueBefore.add(action);
        this.actionRecordsByDate.put(localDate, valueBefore);
    }
}
