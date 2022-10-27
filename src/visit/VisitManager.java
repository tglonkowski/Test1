package visit;

import doctor.Doctor;
import patient.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitManager {

    private final VisitsFileReader visitsFileReader;

    public VisitManager() {
        this.visitsFileReader = new VisitsFileReader();
    }


    public List<Visit> getVisits(List<Doctor> doctors, List<Patient> patients) {
        return visitsFileReader.loadVisits(doctors, patients);
    }

    public Integer getYearWithTheMostVisits(List<Visit> visits) {
        Map<Integer, Integer> groupNumberOfVisits = getGroupNumberOfVisits(visits);
        Integer yearWithMostVisits = new ArrayList<>(groupNumberOfVisits.keySet()).get(0);
        for (Map.Entry<Integer, Integer> year : groupNumberOfVisits.entrySet()) {
            if (year.getValue() > groupNumberOfVisits.get(yearWithMostVisits)) {
                yearWithMostVisits = year.getKey();
            }
        }
        return yearWithMostVisits;
    }

    private Map<Integer, Integer> getGroupNumberOfVisits(List<Visit> visits) {
        if (visits == null || visits.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Map<Integer, Integer> numberOfYearOfVisits = new HashMap<>();
        for (Visit visit : visits) {
            int year = visit.getDateOfVisit().getYear();
            if (!numberOfYearOfVisits.containsKey(year)) {
                int firstVisit = 1;
                numberOfYearOfVisits.put(year, firstVisit);
            }
            Integer numberOfVisitsAtYear = numberOfYearOfVisits.get(year);
            numberOfYearOfVisits.put(year, ++numberOfVisitsAtYear);
        }
        return numberOfYearOfVisits;
    }
}
