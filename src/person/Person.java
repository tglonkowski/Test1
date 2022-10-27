package person;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String personalIDNumber;

    protected Person(Long id, String name, String lastName, LocalDate dateOfBirth, String personalIDNumber) {
        if (!personalIDNumber.matches("\\d{11}")) throw new IllegalArgumentException();
        if (!getBirthDayFromPersonalIdNumber(personalIDNumber).equals(dateOfBirth)) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.personalIDNumber = personalIDNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPersonalIDNumber() {
        return personalIDNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(personalIDNumber, person.personalIDNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, dateOfBirth, personalIDNumber);
    }

    private LocalDate getBirthDayFromPersonalIdNumber(String personalIDNumber) {
        int day = Integer.parseInt(personalIDNumber.substring(4, 6));
        int month = Integer.parseInt(personalIDNumber.substring(2, 4));
        int year = Integer.parseInt(personalIDNumber.substring(0, 2));

        if (month > 12) {
            month -= 20;
            year += 2000;
        } else {
            year += 1900;
        }
        return LocalDate.of(year, month, day);
    }
}
