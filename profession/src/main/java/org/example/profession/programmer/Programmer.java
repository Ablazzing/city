package org.example.profession.programmer;

import lombok.NonNull;
import org.example.profession.Employee;

public class Programmer extends Employee {
    private static final Integer MIN_SALARY = 1500;
    private static final Integer MAX_SALARY = 2000;

    public Programmer(@NonNull String firstName, @NonNull String lastName,
                      @NonNull String middleName, @NonNull Boolean isMale) {
        super(firstName, lastName, middleName, isMale);
    }

    public void doTask(Task task) {
        task.setDone(true);
    }

    @Override
    public void setHourRate(Integer hourRate) {
        if (hourRate < MIN_SALARY || hourRate > MAX_SALARY) {
            throw new RuntimeException("Hour rate is not in interval for this profession");
        }
        super.setHourRate(hourRate);
    }
}
