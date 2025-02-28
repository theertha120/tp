package seedu.internsprint.internship;

public class Internship {
    protected String companyName;
    protected String role;

    public Internship(String companyName, String role) {
        this.companyName = companyName;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role;
    }
}
