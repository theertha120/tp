package seedu.internsprint.internship;

import seedu.internsprint.handler.StorageHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the list of internships.
 */
public class InternshipList {
    protected final HashMap<String, ArrayList<Internship>> internshipMap = new HashMap<>();
    protected int internshipCount = 0;
    private final StorageHandler storageHandler = new StorageHandler();

    public InternshipList() {
        internshipMap.put("software", new ArrayList<>());
        internshipMap.put("hardware", new ArrayList<>());
        internshipMap.put("general", new ArrayList<>());
    }

    /**
     * Adds an internship to the list.
     *
     * @param internship Internship to be added.
     */
    public void addInternship(Internship internship) {
        String type = internship.getType();
        internshipMap.get(type).add(internship);
        internshipCount++;
    }

    /**
     * Deletes an internship from the list.
     *
     * @param type Type of internship.
     * @param index Index of internship to be deleted.
     */
    public void deleteInternship(String type, int index) {
        internshipMap.get(type).remove(index);
        internshipCount--;
    }

    /**
     * Checks if the list contains the internship.
     *
     * @param internship Internship to be checked.
     * @return True if the internship is in the list, false otherwise.
     */
    public boolean contains(Internship internship) {
        String type = internship.getType();
        return internshipMap.get(type).contains(internship);
    }

    public int getInternshipCount() {
        return internshipCount;
    }

    /**
     * Saves the internships to the storage.
     */
    public void saveInternships() {
        storageHandler.saveInternships(this);
    }

    public HashMap<String, ArrayList<Internship>> getInternshipMap() {
        return internshipMap;
    }
}
