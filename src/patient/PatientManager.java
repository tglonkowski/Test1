package patient;

import doctor.Doctor;
import visit.Visit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatientManager {

    private final PatientFileReader patientFileReader;

    public PatientManager() {
        this.patientFileReader = new PatientFileReader();
    }

    public List<Patient> getPatients() {
        return patientFileReader.loadPatients();
    }

    public Patient findPatientWhoHasTheMostVisits(List<Patient> patients) {
        if (patients == null || patients.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Patient patientWhoHasTheMostVisits = patients.get(0);
        for (Patient patient : patients) {
            if (patient.getVisits().size() > patientWhoHasTheMostVisits.getVisits().size()) {
                patientWhoHasTheMostVisits = patient;
            }
        }
        return patientWhoHasTheMostVisits;
    }

    public List<Patient> findPatientsWhoHaveVisitsWithFiveDifferentDoctors(List<Patient> patients) {
        if (patients == null) {
            throw new IllegalArgumentException();
        }
        List<Patient> patientsToFind = new ArrayList<>();
        for (Patient patient : patients) {
            Set<Doctor> doctors = getUniqueDoctors(patient);
            if (doctors.size() >= 5) {
                patientsToFind.add(patient);
            }
        }
        return patientsToFind;
    }

    private Set<Doctor> getUniqueDoctors(Patient patient) {
        Set<Doctor> doctors = new HashSet<>();
        for (Visit visit : patient.getVisits()) {
            Doctor doctor = visit.getDoctor();
            doctors.add(doctor);
        }
        return doctors;
    }
}
