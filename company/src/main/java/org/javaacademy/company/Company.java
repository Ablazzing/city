package org.example.company;

import lombok.NonNull;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.example.profession.Employee;
import org.example.profession.Manager;
import org.example.profession.programmer.Programmer;
import org.example.profession.programmer.Task;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;


public class Company {
    @NonNull private final String name;
    @NonNull private Deque<Programmer> programmers;
    @NonNull private Manager manager;
    private final MultiValuedMap<Programmer, Task> completedTasks = new ArrayListValuedHashMap<>();
    private final HashMap<Employee, BigDecimal> hoursJournal = new HashMap<>();
    private BigDecimal costs = new BigDecimal(0);
    private static final BigDecimal MANAGER_COEFFICIENT = new BigDecimal("0.1");

    public Company(@NonNull String name,
                   @NonNull Deque<Programmer> programmers,
                   @NonNull Manager manager,
                   @NonNull Integer hourRate) {
        this.name = name;
        programmers.forEach(programmer -> programmer.setHourRate(hourRate));
        this.programmers = programmers;
        this.manager = manager;
    }

    public void weekWork(@NonNull Collection<Task> tasks) {
        tasks.forEach(this::doTask);
    }

    public void paySalary() {
        hoursJournal.forEach(this::paySalary);
        hoursJournal.clear();
    }

    private void paySalary(Employee employee, BigDecimal hours) {
        BigDecimal payedSalary = hours.multiply(BigDecimal.valueOf(employee.getHourRate()));
        costs = costs.add(payedSalary);
        employee.payHours(hours);
    }

    private void doTask(@NonNull Task task) {
        Programmer programmer = programmers.pollFirst();
        programmer.doTask(task);
        completedTasks.put(programmer, task);
        addWorkHours(programmer, BigDecimal.valueOf(task.getWorkHours()));
        addWorkHours(manager, MANAGER_COEFFICIENT.multiply(BigDecimal.valueOf(task.getWorkHours())));
        programmers.add(programmer);
    }

    private void addWorkHours(Employee employee, BigDecimal taskHours) {
        BigDecimal hours = hoursJournal.getOrDefault(employee, ZERO)
                .add(taskHours);
        hoursJournal.put(employee, hours);
    }

    public void printInfo() {
        String work = completedTasks.asMap().entrySet().stream()
                .map(e -> e.getKey().getFullName() + " - " + e.getValue().stream().map(Task::getDescription).toList())
                .collect(Collectors.joining("\n"));
        String text = """
                %s
                Затраты: %.2f
                Список выполненных задач у компании:
                %s
                """.formatted(name, costs, work);
        System.out.println(text);
    }
}
