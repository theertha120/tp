package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static seedu.internsprint.util.InternSprintMessages.LIST_MESSAGE_SUCCESS;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists every saved internship in your list, arranged "
            + "by categories\n"
            + "    Parameters: None\n"
            + "    Example: " + COMMAND_WORD;

    @Override
    protected boolean isValidParameters() {
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internshipList) {
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        int count = 1;
        feedback.add(LIST_MESSAGE_SUCCESS);

        feedback.add("Software Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("software")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("Hardware Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("hardware")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("General Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("general")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;

    }
}

