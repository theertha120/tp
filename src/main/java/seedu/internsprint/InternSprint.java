package seedu.internsprint;

import seedu.internsprint.command.Command;
import seedu.internsprint.command.CommandResult;
import seedu.internsprint.handler.Parser;
import seedu.internsprint.handler.StorageHandler;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.Ui;

/**
 * Entry point of the InternSprint application.
 */
public class InternSprint {
    private final InternshipList internships;

    public InternSprint() {
        internships = new InternshipList();
    }

    /**
     * Main entry-point for the InternSprint application.
     */
    public static void main(String[] args) {
        new InternSprint().run();
    }

    /**
     * Runs the InternSprint program until termination.
     */
    public void run() {
        Ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        CommandResult result = StorageHandler.loadInternships(internships);
        Ui.showResultToUser(result);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = Ui.getUserCommand();
                Command command = Parser.parseCommand(userCommand);
                result = command.execute(internships);
                Ui.showResultToUser(result);
                isExit = result.isExit();
            } catch (IllegalArgumentException e) {
                Ui.showError(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Exits the program after displaying the exit message.
     */
    private void exit() {
        Ui.showExitMessage();
        System.exit(0);
    }
}
