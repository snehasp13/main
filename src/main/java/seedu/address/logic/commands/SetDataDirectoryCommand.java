package seedu.address.logic.commands;

import java.io.File;
import java.util.Optional;

import seedu.address.model.Model;

/**
 * Command that changes the directory where application data is stored.
 */
public class SetDataDirectoryCommand implements Command {

    private static final String MESSAGE_CHANGE = "Data directory changed to: %s";

    private final File newDir;

    public SetDataDirectoryCommand(File newDir) {
        assert newDir.isAbsolute();
        this.newDir = newDir;
    }

    @Override
    public CommandResult execute(Model model) {
        final File newTaskBookFile = new File(newDir, "taskbook.json");
        model.setTaskBookFilePath(newTaskBookFile.getAbsolutePath());
        model.setTaskSelect(Optional.empty());
        return new CommandResult(String.format(MESSAGE_CHANGE, newDir.getAbsolutePath()));
    }

}
