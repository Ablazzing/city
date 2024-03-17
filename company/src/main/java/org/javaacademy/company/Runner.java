package org.example.company;

import org.example.profession.Manager;
import org.example.profession.programmer.Programmer;
import org.example.profession.programmer.Task;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class Runner {
    public static void main(String[] args) {
        Programmer programmer = new Programmer("Yuri", "Ivanov", "Ivanovich", true);
        Programmer programmer2 = new Programmer("Gena", "Shum", "Ivanovich", true);
        Manager manager = new Manager("Petr", "Petrov", "Ivanovich", true);
        Company company = new Company("Oracle", new LinkedList<>(List.of(programmer, programmer2)), manager, 1700);
        Task task = new Task("Увеличить шрифт", 3);
        Task task2 = new Task("Увеличить шрифт3", 3);
        company.weekWork(List.of(task, task2));
        company.paySalary();
        company.printInfo();
    }
}
