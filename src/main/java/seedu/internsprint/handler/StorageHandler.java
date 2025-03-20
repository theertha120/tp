package seedu.internsprint.handler;

import seedu.internsprint.command.CommandResult;
import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.SoftwareInternship;
import seedu.internsprint.internship.InternshipList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static seedu.internsprint.util.InternSprintExceptionMessages.FILE_ALREADY_EXISTS;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_WRITE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_READ_FILE;

import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_FIRST_TIME;

/**
 * Handles the reading and writing of data to the file.
 */
public class StorageHandler {
    private static final String FILE_PATH = Paths.get("data", "internships.txt").toString();
    private static File file;

    public StorageHandler() {
        file = new File(FILE_PATH);
    }

    /**
     * Creates the file if it does not exist.
     */
    public static void createFile() {
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException(String.format(UNABLE_TO_CREATE_DIRECTORY,
                            file.getParentFile().getAbsolutePath()));
                }
                if (!file.createNewFile()) {
                    throw new RuntimeException(String.format(FILE_ALREADY_EXISTS,
                            file.getAbsolutePath()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_CREATE_FILE,
                    file.getAbsolutePath()));
        }
    }

    /**
     * Saves the internships to the file.
     *
     * @param internships List of internships to be saved.
     */
    public void saveInternships(InternshipList internships) {
        JSONArray jsonArray = new JSONArray();
        internships.getInternshipMap().forEach((type, list) -> {
            list.forEach(internship -> jsonArray.put(internship.toJson()));
        });

        if (!file.exists()) {
            createFile();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4));
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_WRITE_FILE,
                    file.getAbsolutePath()));
        }
    }

    /**
     * Loads the internships from the file.
     *
     * @param internships List of internships to be loaded.
     * @return CommandResult object indicating the success of the operation.
     */
    public static CommandResult loadInternships(InternshipList internships) {
        CommandResult result;
        if (!file.exists() || file.length() == 0) {
            result = new CommandResult(LOADING_DATA_FIRST_TIME);
            result.setSuccessful(true);
            return result;
        }
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            result = errorReadingFile();
            return result;
        }

        JSONArray jsonArray = new JSONArray(jsonData.toString());
        if (jsonArray.isEmpty()) {
            result = errorReadingFile();
            return result;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject internshipJson = jsonArray.getJSONObject(i);
            addInternshipToList(internships, internshipJson);
        }
        result = new CommandResult(LOADING_DATA_SUCCESS);
        result.setSuccessful(true);
        return result;
    }

    /**
     * Returns a CommandResult object indicating that there was an error reading the file.
     *
     * @return CommandResult object indicating the error.
     */
    private static CommandResult errorReadingFile() {
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        feedback.add(UNABLE_TO_READ_FILE);
        result = new CommandResult(feedback);
        result.setSuccessful(false);
        return result;
    }

    /**
     * Adds the internship to the list of internships.
     *
     * @param internships List of internships.
     * @param internshipJson JSON object representing the internship.
     */
    private static void addInternshipToList(InternshipList internships, JSONObject internshipJson) {
        switch (internshipJson.getString("type")) {
        case "general":
            internships.addInternship(GeneralInternship.fromJson(internshipJson));
            break;
        case "software":
            internships.addInternship(SoftwareInternship.fromJson(internshipJson));
            break;
        case "hardware":
            internships.addInternship(HardwareInternship.fromJson(internshipJson));
            break;
        default:
            break;
        }
    }
}


