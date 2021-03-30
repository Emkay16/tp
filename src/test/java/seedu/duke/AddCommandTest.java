package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddCommandTest {
    @Test
    public void executeAddCommand_patientAdded() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "add");
        arguments.put("payload", "S1234567D");
        AddCommand addCommand = new AddCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            addCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals("Patient S1234567D has been added!" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }

    @Test
    public void executeAddCommand_invalidID_exceptionThrown() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "add");
        arguments.put("payload", "S12345677A");
        AddCommand addCommand = new AddCommand(ui, data, arguments);

        Exception exception = assertThrows(Exception.class, () -> {
            addCommand.execute();
        });
        assertEquals("Please key in a valid NRIC number!", exception.getMessage());
    }

    @Test
    public void executeAddCommand_patientAlreadyExists_exceptionThrown() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "add");
        arguments.put("payload", "S1234567D");
        AddCommand addCommand = new AddCommand(ui, data, arguments);

        try {
            addCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        Exception exception = assertThrows(Exception.class, () -> {
            addCommand.execute();
        });
        assertEquals("Patient already exists!", exception.getMessage());
    }
}
