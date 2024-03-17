package org.example.profession;

import lombok.Getter;
import lombok.NonNull;
import org.javaacademy.human.Human;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Getter
public abstract class Employee extends Human {
    private Integer hourRate;
    protected BigDecimal totalSalary = ZERO;


    public Employee(@NonNull String firstName, @NonNull String lastName,
                    @NonNull String middleName, @NonNull Boolean isMale) {
        super(firstName, lastName, middleName, isMale);
    }

    public Employee(@NonNull String firstName, @NonNull String lastName, @NonNull String middleName,
                    @NonNull Boolean isMale, Integer hourRate) {
        super(firstName, lastName, middleName, isMale);
        this.hourRate = hourRate;
    }

    public void payHours(BigDecimal countHours) {
        if (countHours.compareTo(ZERO) < 1) {
            throw new RuntimeException("Count hours zero or minus");
        }
        totalSalary = totalSalary.add(countHours.multiply(valueOf(hourRate)));
    }

    public void setHourRate(Integer hourRate) {
        this.hourRate = hourRate;
    }
}
