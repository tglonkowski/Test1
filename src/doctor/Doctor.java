package doctor;

import person.Person;
import visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String specialization;
    private String nip;

    private List<Visit> visits = new ArrayList<>();

    public Doctor(Long id, String name, String lastName, LocalDate dateOfBirth, String personalIDNumber, String specialization, String nip) {
        super(id, name, lastName, dateOfBirth, personalIDNumber);
        if (!nip.matches("^((\\d{3}[- ]\\d{3}[- ]\\d{2}[- ]\\d{2})|(\\d{3}[- ]\\d{2}[- ]\\d{2}[- ]\\d{3}))$"))
            throw new IllegalArgumentException();
        this.specialization = specialization;
        this.nip = nip;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public static Integer getAge(Doctor doctor) {
        int yearOfBirth = doctor.getDateOfBirth().getYear();
        int now = LocalDate.now().getYear();
        return now - yearOfBirth;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", nip='" + nip + '\'' +
                ", personalIDNumber='" + getPersonalIDNumber() + '\'' +
                '}';
    }
}
