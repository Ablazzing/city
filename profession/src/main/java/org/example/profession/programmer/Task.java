package org.example.profession.programmer;

import lombok.Data;
import lombok.NonNull;


@Data
public class Task {
    private boolean isDone;
    private @NonNull String description;
    private @NonNull Integer workHours;
}
