package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static seedu.internsprint.util.InternSprintMessages.ADD_MESSAGE_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.MESSAGE_DUPLICATE_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.LIST_COUNT_MESSAGE;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to the internship list.\n"
            + "Parameters: " + "/c COMPANY_NAME " + "/r ROLE\n"
            + "Example: " + COMMAND_WORD + " /c Google " + "/r Software Engineer";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r"};

    protected boolean isValidParameters() {
        return parameters.keySet().containsAll(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        if (!isValidParameters()) {
            result = new CommandResult(MESSAGE_USAGE);
            result.setSuccessful(false);
            return result;
        }

        Internship toAdd = new Internship(parameters.get("/c"), parameters.get("/r"));

        if (internships.contains(toAdd)) {
            result = new CommandResult(MESSAGE_DUPLICATE_INTERNSHIP);
            result.setSuccessful(false);
            return result;
        }

        List<String> feedback = new ArrayList<>();

        internships.addInternship(toAdd);
        feedback.add(ADD_MESSAGE_SUCCESS);
        feedback.add(toAdd.toString());
        feedback.add(String.format(LIST_COUNT_MESSAGE, internships.internshipCount));
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
