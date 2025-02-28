package seedu.internsprint.handler;

import seedu.internsprint.command.AddCommand;
import seedu.internsprint.command.ByeCommand;
import seedu.internsprint.command.Command;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parseCommand(String userInput) {
        String[] commandTypeAndParams = userInput.trim().split(" ", 2);
        String commandType = commandTypeAndParams[0];
        String params = commandTypeAndParams.length > 1 ? commandTypeAndParams[1] : "";

        Command command;
        switch (commandType) {
        case "add":
            command = new AddCommand();
            break;
        case "bye":
            command = new ByeCommand();
            break;
        default:
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
        parseKeyValuePairs(params, command);
        return command;
    }

    private static void parseKeyValuePairs(String params, Command command) {
        HashMap<String, String> keyValueMap = new HashMap<>();

        if (params.isEmpty()) {
            return;
        }

        String[] parts = params.trim().split("/", 2);
        String description = parts[0].trim();
        if (!description.isEmpty()) {
            keyValueMap.put("description", description);
        }
        if (parts.length == 1) {
            command.setParameters(keyValueMap);
            return;
        }

        String keyValuePart = "/" + parts[1];
        String regex = "(/\\w+)\\s+(.*?)(?=\\s+/|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(keyValuePart);

        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            if (value.contains("/")) {
                throw new IllegalArgumentException(InternSprintExceptionMessages.ILLEGAL_COMMAND_INPUT);
            }
            keyValueMap.put(key, value);
        }

        String unmatched = matcher.replaceAll("").trim();
        if (!unmatched.isEmpty()) {
            throw new IllegalArgumentException(String.format(InternSprintExceptionMessages.MISSING_COMMAND_INPUT, unmatched));
        }

        command.setParameters(keyValueMap);
    }
}
