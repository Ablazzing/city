package org.javaacademy.example.civilregistry;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.example.citizen.Citizen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.javaacademy.example.civilregistry.CitizenActionType.BIRTH;
import static org.javaacademy.example.civilregistry.CitizenActionType.DIVORCE;
import static org.javaacademy.example.civilregistry.CitizenActionType.MARRIAGE;
import static org.javaacademy.human.Human.checkHumansIsDifferentSex;

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
        CitizenActionRecord actionRecord = new CitizenActionRecord(MARRIAGE, registerDate, Set.of(human, anotherHuman));
        addActionForDate(registerDate, actionRecord);
    }

    public void registerDivorce(Citizen human, Citizen anotherHuman, LocalDate registerDate) {
        checkCitizensIsCouple(human, anotherHuman);
        human.setCoupleHuman(null);
        anotherHuman.setCoupleHuman(null);
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
        if (human == null || !human.getCoupleHuman().equals(anotherHuman)) {
            throw new RuntimeException("Humans not a couple");
        }
    }

    private void addActionForDate(LocalDate localDate, CitizenActionRecord action) {
        List<CitizenActionRecord> valueBefore = this.actionRecordsByDate.getOrDefault(localDate, new ArrayList<>());
        valueBefore.add(action);
        this.actionRecordsByDate.put(localDate, valueBefore);
    }
}
