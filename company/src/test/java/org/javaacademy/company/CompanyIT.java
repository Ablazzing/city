package org.example.company;

import org.example.profession.Manager;
import org.example.profession.programmer.Programmer;
import org.example.profession.programmer.Task;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyIT {
    private Manager manager = new Manager("test", "test", "test", true);
    private Programmer programmer = new Programmer("test", "test", "test", true);
    private LinkedList<Programmer> programmers = new LinkedList<>(List.of(programmer));

    @Test
    void programerSalaryLowException() {
        assertThrows(RuntimeException.class,
                () -> new Company("test", programmers, manager, 0));
        assertThrows(RuntimeException.class,
                () -> new Company("test", programmers, manager, 2100));
    }

    @Test
    void doTasksAndPaySalary() {
        Company company = new Company("test", programmers, manager, 1500);
        Task task = new Task("test", 10);
        assertDoesNotThrow(() -> company.weekWork(List.of(task)));
        assertDoesNotThrow(company::paySalary);
        assertDoesNotThrow(company::printInfo);
    }

}
