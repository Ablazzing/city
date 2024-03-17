package org.example.profession;

import lombok.NonNull;

public class Manager extends Employee {
    private static final int HOUR_RATE = 10_000;

    public Manager(@NonNull String firstName, @NonNull String lastName,
                   @NonNull String middleName, @NonNull Boolean isMale) {
        super(firstName, lastName, middleName, isMale, HOUR_RATE);
    }
}
