package org.javaacademy.human;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = {"firstName", "lastName", "middleName"})
public class Human {
    @NonNull
    protected String firstName;
    @NonNull
    protected String lastName;
    @NonNull
    protected String middleName;
    @NonNull
    protected Boolean isMale;
    protected Human father;
    protected Human mother;
    protected Set<Human> children = new HashSet<>();

    public void setParents(Human father, Human mother) {
        if (!father.getIsMale()) {
            throw new RuntimeException("father is not male");
        }
        if (mother.isMale) {
            throw new RuntimeException("mother is not female");
        }
        this.mother = mother;
        this.father = father;
        mother.children.add(this);
        father.children.add(this);
    }

    public String getFullName() {
        return String.format("%s %s %s", firstName, lastName, middleName);
    }

    public static void checkHumansIsDifferentSex(Human human, Human anotherHuman) {
        if (human.getIsMale() == anotherHuman.getIsMale()) {
            throw new RuntimeException("Human is same sex");
        }
    }
}
