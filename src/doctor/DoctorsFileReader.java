package doctor;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DoctorsFileReader {

    private static final String DOCTORS_FILE = "lekarze.txt";

    List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (FileReader fileReader = new FileReader(DOCTORS_FILE); Scanner sc = new Scanner(fileReader)) {
            skipFirstLine(sc);
            while (sc.hasNext()) {
                String[] array = sc.nextLine().split("\t");
                Doctor doctor = new Doctor(parseId(array), name(array), lastName(array), parseDateOfBirth(array[4]), personalIDNumber(array), specialization(array),nip(array));
                doctors.add(doctor);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return doctors;
    }

    private String nip(String[] split) {
        return split[5];
    }

    private String specialization(String[] split) {
        return split[3];
    }

    private String personalIDNumber(String[] array) {
        return array[6];
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
