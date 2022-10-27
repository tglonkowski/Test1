package patient;

import person.Person;
import visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<Visit> visits = new ArrayList<>();

    public Patient(Long id, String name, String lastName, LocalDate dateOfBirth, String personalIDNumber) {
        super(id, name, lastName, dateOfBirth, personalIDNumber);
    }

    public List<Visit> getVisits() {
        return visits;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", personalIDNumber='" + getPersonalIDNumber() + '\'' +
                '}';
    }
}
