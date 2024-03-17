package org.javaacademy.example.civilregistry;

import org.javaacademy.example.citizen.Citizen;

import java.time.LocalDate;
import java.util.Set;

public record CitizenActionRecord(CitizenActionType actionType,
                                  LocalDate registrationDate,
                                  Set<Citizen> humansInAction) {
}
