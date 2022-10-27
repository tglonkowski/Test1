package visit;

import doctor.Doctor;
import patient.Patient;


import java.time.LocalDate;

public class Visit {

    private final Doctor doctor;
    private final Patient patient;
    private final LocalDate dateOfVisit;

    public Visit(Doctor doctor, Patient patient, LocalDate dateOfVisit) {
        if (doctor == null || patient == null) {
            throw new IllegalArgumentException();
        }
        this.doctor = doctor;
        this.patient = patient;
        this.dateOfVisit = dateOfVisit;
        doctor.getVisits().add(this);
        patient.getVisits().add(this);
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", dateOfVisit=" + dateOfVisit +
                '}';
    }
}
