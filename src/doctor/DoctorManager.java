package doctor;

import patient.Patient;
import visit.Visit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoctorManager {

    private final DoctorsFileReader doctorsFileReader;

    public DoctorManager() {
        this.doctorsFileReader = new DoctorsFileReader();
    }

    public List<Doctor> getDoctors() {
        return doctorsFileReader.loadDoctors();
    }

    public Doctor findDoctorWhoHasTheMostVisits(List<Doctor> doctors) {
        if (doctors == null || doctors.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Doctor doctorWithMostVisits = doctors.get(0);
        for (Doctor doctor : doctors) {
            if (doctor.getVisits().size() > doctorWithMostVisits.getVisits().size()) {
                doctorWithMostVisits = doctor;
            }
        }
        return doctorWithMostVisits;
    }

    public List<Doctor> findTopFiveOldestDoctors(List<Doctor> doctors) {
        if (doctors == null) {
            throw new IllegalArgumentException();
        }
        List<Doctor> sortedDoctors = sortedDoctorsByAge(doctors);
        return sortedDoctors.stream().limit(5).toList();
    }

    public String findTheMostPopularSpecialization(List<Doctor> doctors) {
        Map<String, Integer> groupSpecializationByNumbers = groupSpecializationByNumbers(doctors);
        String mostPopularSpeciality = new ArrayList<>(groupSpecializationByNumbers.keySet()).get(0);
        for (Map.Entry<String, Integer> specialization : groupSpecializationByNumbers.entrySet()) {
            if (specialization.getValue() > groupSpecializationByNumbers.get(mostPopularSpeciality)) {
                mostPopularSpeciality = specialization.getKey();
            }
        }
        return mostPopularSpeciality;
    }

    public List<Doctor> findExclusiveDoctors(List<Doctor> doctors) {
        if (doctors == null) {
            throw new IllegalArgumentException();
        }
        List<Doctor> exclusiveDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            Set<Patient> uniquePatients = getUniquePatients(doctor);
            if (uniquePatients.size() == 1) {
                exclusiveDoctors.add(doctor);
            }
        }
        return exclusiveDoctors;
    }

    private Set<Patient> getUniquePatients(Doctor doctor) {
        Set<Patient> uniquePatients = new HashSet<>();
        for (Visit visit : doctor.getVisits()) {
            Patient patient = visit.getPatient();
            uniquePatients.add(patient);
        }
        return uniquePatients;
    }

    private Map<String, Integer> groupSpecializationByNumbers(List<Doctor> doctors) {
        if (doctors == null || doctors.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Map<String, Integer> specializations = new HashMap<>();
        for (Doctor doctor : doctors) {
            String doctorSpecialization = doctor.getSpecialization();
            if (!specializations.containsKey(doctorSpecialization)) {
                specializations.put(doctorSpecialization, 1);
            }
            Integer numberOfSpecialization = specializations.get(doctorSpecialization);
            specializations.put(doctorSpecialization, ++numberOfSpecialization);
        }
        return specializations;
    }

    private List<Doctor> sortedDoctorsByAge(List<Doctor> doctors) {
        List<Doctor> oldestDoctorsToFind = new ArrayList<>(doctors);
        oldestDoctorsToFind.sort(Comparator.comparing(Doctor::getAge).reversed());
        return oldestDoctorsToFind;
    }
}
