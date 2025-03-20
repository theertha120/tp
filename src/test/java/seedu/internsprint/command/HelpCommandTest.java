package seedu.internsprint.command;

import org.junit.jupiter.api.Test;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.InternSprintExceptionMessages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HelpCommandTest {
    @Test
    public void helpCommand_provideEmptyParameter_returnsValid() {
        HelpCommand helpCommand = new HelpCommand();
        CommandResult result = helpCommand.execute(new InternshipList());
        assertTrue(result.isSuccessful());
    }

    @Test
    public void helpCommand_provideCommandParameter_returnsValid() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.parameters.put("command", "add general");
        CommandResult result = helpCommand.execute(new InternshipList());
        assertTrue(result.isSuccessful());
        assertEquals("-> "+AddGeneralCommand.MESSAGE_USAGE, result.getFeedbackToUser().get(0));
    }

    @Test
    public void helpCommand_provideInvalidCommand_returnsInvalid() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.parameters.put("command", "randomCommand");
        CommandResult result = helpCommand.execute(new InternshipList());
        assertFalse(result.isSuccessful());
        assertEquals(InternSprintExceptionMessages.HELP_INVALID_PARAMETERS, result.getFeedbackToUser().get(0));
    }
}
