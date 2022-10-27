package visit;

import doctor.Doctor;
import patient.Patient;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class VisitsFileReader {

    private static final String VISITS_FILE = "wizyty.txt";

    List<Visit> loadVisits(List<Doctor> doctors, List<Patient> patients) {
        if (doctors == null || patients == null) {
            throw new IllegalArgumentException();
        }
        List<Visit> visits = new ArrayList<>();
        try (FileReader fileReader = new FileReader(VISITS_FILE);
             Scanner sc = new Scanner(fileReader)) {
            skipFirstLine(sc);
            while (sc.hasNext()) {
                String[] array = sc.nextLine().split("\t");
                Visit visit = new Visit(getDoctorById(array, doctors), getPatientById(array, patients), parseDateOfBirth(array));
                visits.add(visit);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return visits;
    }

    private Patient getPatientById(String[] array, List<Patient> patients) {
        if (patients.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Patient patientToFind = patients.get(0);
        for (Patient patient : patients) {
            if (patient.getId().equals(parsePatientId(array))) {
                patientToFind = patient;
            }
        }
        return patientToFind;
    }

    private Doctor getDoctorById(String[] array, List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Doctor doctorToFind = doctors.get(0);
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(parseDoctorId(array))) {
                doctorToFind = doctor;
            }
        }
        return doctorToFind;
    }

    private Long parseDoctorId(String[] array) {
        return Long.parseLong(array[0]);
    }

    private Long parsePatientId(String[] array) {
        return Long.parseLong(array[1]);
    }

    private void skipFirstLine(Scanner sc) {
        sc.nextLine();
    }

    private LocalDate parseDateOfBirth(String[] array) {
        return LocalDate.parse(array[2], DateTimeFormatter.ofPattern("yyyy-M-d"));
    }
}
