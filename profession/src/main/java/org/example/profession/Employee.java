import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.human.Human;


public class Employee extends Human {

    public Employee(@NonNull String firstName, @NonNull String lastName, @NonNull String middleName, @NonNull Boolean isMale) {
        super(firstName, lastName, middleName, isMale);
    }
}
