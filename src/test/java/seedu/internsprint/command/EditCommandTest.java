package seedu.internsprint.command;

import org.junit.jupiter.api.Test;
import seedu.internsprint.internship.SoftwareInternship;
import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.InternshipList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internsprint.util.InternSprintMessages.MESSAGE_DUPLICATE_INTERNSHIP;


class EditCommandTest {

    @Test
    void isValidParameters_provideCorrectIndexAndFlags_returnsValid() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "Java");
        editCommand.parameters.put("/eli", "Y3 students");
        editCommand.parameters.put("/r","/Automation Testing Intern");
        editCommand.parameters.put("/tech", "Java, C, C++");
        editCommand.parameters.put("/dept","Quality Assurance");
        assertTrue(editCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideNoIndex_returnsInvalid() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/c", "Java");
        editCommand.parameters.put("/eli", "Y3 students");
        editCommand.parameters.put("/r","/Automation Testing Intern");
        editCommand.parameters.put("/tech", "Java, C, C++");
        editCommand.parameters.put("/dept","Quality Assurance");
        assertFalse(editCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideInvalidFlag_returnsInvalid() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/c", "Java");
        editCommand.parameters.put("/eli", "Y3 students");
        editCommand.parameters.put("/r","/Automation Testing Intern");
        editCommand.parameters.put("/tech", "Java, C, C++");
        editCommand.parameters.put("/deadline","27th January");
        assertFalse(editCommand.isValidParameters());
    }

    @Test
    void execute_editCompanyAndRoleForSoftwareInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "Java");
        editCommand.parameters.put("/r","Automation Testing Intern");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Software Engineering", "SWE");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        editCommand.execute(internshipList);
        assertEquals("Java", internship.getCompanyName());
        assertEquals("Automation Testing Intern", internship.getRole());
        assertEquals("software", internship.getType());
    }

    @Test
    void execute_editCompanyAndRoleForGeneralInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "UBS");
        editCommand.parameters.put("/r","IT Intern");
        GeneralInternship internship = new GeneralInternship("Facebook","Tech Support", "IT");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        editCommand.execute(internshipList);
        assertEquals("UBS", internship.getCompanyName());
        assertEquals("IT Intern", internship.getRole());
        assertEquals("general", internship.getType());
    }

    @Test
    void execute_editCompanyAndRoleForHarwareInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "Xilinx");
        editCommand.parameters.put("/r","Engineering Intern");
        HardwareInternship internship = new HardwareInternship("Facebook","Automation Expert", "C");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        editCommand.execute(internshipList);
        assertEquals("Xilinx", internship.getCompanyName());
        assertEquals("Engineering Intern", internship.getRole());
        assertEquals("hardware", internship.getType());
    }

    @Test
    void execute_multipleInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "2");
        editCommand.parameters.put("/eli","Good knowledge of microcontrollers");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Automation Intern", "C");
        HardwareInternship internship2 = new HardwareInternship("AMD","Engineer", "Arduino");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        internshipList.addInternship(internship2);
        editCommand.execute(internshipList);
        assertEquals("Good knowledge of microcontrollers", internship2.getEligibility());
        assertEquals("AMD", internship2.getCompanyName());
    }

    @Test
    void execute_invalidFieldForSoftware_throwsError() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "Java");
        editCommand.parameters.put("/dept","SWE Intern");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Automation Intern", "C");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        editCommand.execute(internshipList);
        assertEquals("Java", internship.getCompanyName());
        assertEquals("Automation Intern", internship.getRole());
        assertEquals("C", internship.getTechStack());
        assertEquals("software", internship.getType());
    }

    @Test
    void execute_editInternshipIsDuplicate_returnsError() {
        EditCommand editCommand = new EditCommand();
        editCommand.parameters.put("/index", "1");
        editCommand.parameters.put("/c", "Google");
        editCommand.parameters.put("/r","SDE Intern");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Automation Intern", "C");
        SoftwareInternship internship2 = new SoftwareInternship("Google","SDE Intern", "C");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship);
        internshipList.addInternship(internship2);
        CommandResult result = editCommand.execute(internshipList);
        assertFalse(result.isSuccessful());
        assertEquals(MESSAGE_DUPLICATE_INTERNSHIP, result.getFeedbackToUser().get(0));
        assertEquals("Facebook", internshipList.getInternshipMap().get("software").get(0).getCompanyName());
        assertEquals("Automation Intern", internshipList.getInternshipMap().get("software").get(0).getRole());
    }
}
