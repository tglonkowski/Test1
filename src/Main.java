
import doctor.Doctor;
import doctor.DoctorManager;
import patient.Patient;
import patient.PatientManager;
import visit.Visit;
import visit.VisitManager;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        DoctorManager doctorsManager = new DoctorManager();
        PatientManager patientManager = new PatientManager();
        VisitManager visitsManager = new VisitManager();

        List<Doctor> doctors = doctorsManager.getDoctors();
        List<Patient> patients = patientManager.getPatients();
        List<Visit> visits = visitsManager.getVisits(doctors, patients);

        System.out.println("Doctor who has the most visits:");
        System.out.println(doctorsManager.findDoctorWhoHasTheMostVisits(doctors));
        System.out.println("\nPatient who has the most visits:");
        System.out.println(patientManager.findPatientWhoHasTheMostVisits(patientManager.getPatients()));
        System.out.println("\nTop 5 oldest doctors:");
        System.out.println(doctorsManager.findTopFiveOldestDoctors(doctors));
        System.out.println("\nMost Popular Specialization:");
        System.out.println(doctorsManager.findTheMostPopularSpecialization(doctors));
        System.out.println("\nYear with the most visits:");
        System.out.println(visitsManager.getYearWithTheMostVisits(visits));
        System.out.println("\nPatients who have visits with five different doctors:");
        System.out.println(patientManager.findPatientsWhoHaveVisitsWithFiveDifferentDoctors(patients));
        System.out.println("\nExclusive Doctors:");
        System.out.println(doctorsManager.findExclusiveDoctors(doctors));
    }

}
