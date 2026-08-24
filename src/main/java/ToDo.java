//Todo task type

/**
 * Represents a ToDo task in the Bags application.
 *
 * <p>A ToDo task contains a description and can be marked as done
 * or undone through the functionality inherited from {@link Task}.</p>
 *
 * <p>AI was used to assist in reconfiguring the task creation method
 * into the individual task type classes. The generated code was
 * reviewed and adapted to fit the application's requirements.</p>
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task.
     *
     * @param description text describing the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a to-do task from an add-task command string.
     *
     * @param output user input after {@code todo}
     * @return the created to-do task
     * @throws DukeException if the description is missing
     */
    public static ToDo fromCommand(String output) throws DukeException {
        String[] temp = output.split(" ");

        if (temp.length < 2) {
            throw new DukeException(
                    "Missing task description! Add info after the type of task");
        }

        StringBuilder name = new StringBuilder();

        for (int i = 1; i < temp.length; i++) {
            name.append(temp[i]).append(" ");
        }

        String description = name.toString().trim();

        if (description.isEmpty()) {
            throw new DukeException(
                    "Missing task description! Add info after the type of task");
        }

        return new ToDo(description);
    }

    /**
     * Returns the ToDo task in a user-readable format.
     *
     * @return the formatted ToDo task
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }

}