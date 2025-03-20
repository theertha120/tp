package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;

import java.util.List;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final List<String> feedbackToUser;
    private final List<Internship> relevantInternships;
    private boolean isSuccessful;
    private boolean isExit;

    /**
     * Constructs a {@code CommandResult} with multiple feedback.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     */
    public CommandResult(List<String> feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantInternships = null;
        this.isExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with one feedback.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = List.of(feedbackToUser);
        this.relevantInternships = null;
        this.isExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with multiple feedback and relevant internships.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     * @param relevantInternships Internships relevant to the command.
     */
    public CommandResult(List<String> feedbackToUser, List<Internship> relevantInternships) {
        this.feedbackToUser = feedbackToUser;
        this.relevantInternships = relevantInternships;
        this.isExit = false;
    }

    public List<String> getFeedbackToUser() {
        return feedbackToUser;
    }

    public List<Internship> getRelevantInternships() {
        return relevantInternships;
    }

    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
