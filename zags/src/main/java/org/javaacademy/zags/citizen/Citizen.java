package org.javaacademy.example.citizen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.javaacademy.human.Human;

import java.util.HashSet;

import static org.javaacademy.example.citizen.FamilyStatus.SINGLE;

@ToString
@Getter @Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Citizen extends Human {
    @EqualsAndHashCode.Exclude
    private FamilyStatus familyStatus;
    @EqualsAndHashCode.Exclude
    private Citizen coupleHuman;

    public Citizen(@NonNull String firstName, @NonNull String lastName,
                   @NonNull String middleName,
                   @NonNull Boolean isMale, FamilyStatus familyStatus) {
        super(firstName, lastName, middleName, isMale);
        this.familyStatus = familyStatus;
    }

    public Citizen birthNewHuman(String firstName, String middleName,
                                Boolean isMale, Human anotherHuman) {
        checkHumansIsDifferentSex(this, anotherHuman);
        Citizen child = Citizen.builder()
                .firstName(firstName)
                .lastName(this.lastName)
                .middleName(middleName)
                .familyStatus(SINGLE)
                .isMale(isMale)
                .children(new HashSet<>())
                .build();
        if (this.isMale) {
            child.setParents(this, anotherHuman);
        } else {
            child.setParents(anotherHuman, this);
        }
        return child;
    }
}
