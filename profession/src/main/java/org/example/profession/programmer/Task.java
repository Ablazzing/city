package org.example.profession;

import lombok.Data;
import lombok.NonNull;

import java.util.Random;

@Data
public class Task {
    private boolean isDone;
    private @NonNull String description;
    private int workHours = new Random().nextInt(8) + 1;
}
