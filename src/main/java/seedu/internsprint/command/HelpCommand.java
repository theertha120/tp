package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.LinkedHashMap;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Shows information on how to use a specific command " +
            "or a list of all commands.\n"
            + "    Parameters: [COMMAND] \n"
            + "    Example: " + COMMAND_WORD + " add general";

    private static final LinkedHashMap<String, String> COMMAND_HELP_MESSAGES = new LinkedHashMap<>();

    static {
        COMMAND_HELP_MESSAGES.put("add general", AddGeneralCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("add software", AddSoftwareCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("add hardware", AddHardwareCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("list", ListCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("description", DescriptionCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("delete", DeleteCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("edit", EditCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("help", HelpCommand.MESSAGE_USAGE);
        COMMAND_HELP_MESSAGES.put("bye", ByeCommand.MESSAGE_USAGE);
    }

    @Override
    protected boolean isValidParameters() {
        return parameters.isEmpty() || parameters.size() == 1;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        String feedback;
        boolean isSuccess = false;

        if (!isValidParameters()) {
            feedback = InternSprintExceptionMessages.HELP_INVALID_PARAMETERS;
        } else if (parameters.isEmpty()) {
            StringBuilder helpMessage = new StringBuilder("Here is the guide for all available commands:\n\n");
            for (String command : COMMAND_HELP_MESSAGES.keySet()) {
                helpMessage.append("\t-> ").append(COMMAND_HELP_MESSAGES.get(command)).append("\n\n");
            }
            feedback = helpMessage.toString();
            isSuccess = true;
        } else {
            String commandName = parameters.values().iterator().next();
            if (COMMAND_HELP_MESSAGES.containsKey(commandName)) {
                feedback = "-> "+ COMMAND_HELP_MESSAGES.get(commandName);
                isSuccess = true;
            } else {
                feedback = InternSprintExceptionMessages.HELP_INVALID_PARAMETERS;
            }
        }

        CommandResult result = new CommandResult(feedback);
        result.setSuccessful(isSuccess);

        return result;
    }
}
