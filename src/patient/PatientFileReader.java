package patient;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PatientFileReader {

    private static final String PATIENTS_FILE = "pacjenci.txt";

    List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        try (FileReader fileReader = new FileReader(PATIENTS_FILE); Scanner sc = new Scanner(fileReader)) {
            skipFirstLine(sc);
            while (sc.hasNext()) {
                String[] array = sc.nextLine().split("\t");
                Patient patient = new Patient(parseId(array), name(array), lastName(array), parseDateOfBirth(array[4]), personalIDNumber(array));
                patients.add(patient);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return patients;
    }

    private String personalIDNumber(String[] array) {
        return array[3];
    }

    private String lastName(String[] array) {
        return array[1];
    }

    private String name(String[] array) {
        return array[2];
    }

    private long parseId(String[] array) {
        return Long.parseLong(array[0]);
    }

    private LocalDate parseDateOfBirth(String text) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-M-d"));
    }

    private void skipFirstLine(Scanner sc) {
        sc.nextLine();
    }

}
