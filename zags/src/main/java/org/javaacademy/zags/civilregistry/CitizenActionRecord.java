package org.javaacademy.zags.civilregistry;

import org.javaacademy.zags.citizen.Citizen;

import java.time.LocalDate;
import java.util.Set;

public record CitizenActionRecord(CitizenActionType actionType,
                                  LocalDate registrationDate,
                                  Set<Citizen> humansInAction) {
}
