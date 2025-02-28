package seedu.internsprint.internship;

import java.util.ArrayList;

public class InternshipList {
    private final ArrayList<Internship> internships;
    public int internshipCount = 0;

    public InternshipList() {
        internships = new ArrayList<>();
    }

    public void addInternship(Internship internship) {
        internships.add(internship);
        internshipCount++;
    }

    public boolean contains(Internship internship) {
        return internships.contains(internship);
    }
}
