
/**
 * Main chatbot class for the Bags application.
 */
public class Duke {

    private final Tasklist tasks;
    private final Parser parser;
    private String commandType;

    /**
     * Creates a Duke chatbot with an empty task list.
     */
    public Duke() {
        tasks = new Tasklist();
        parser = new Parser();
    }

    /**
     * Generates a response by matching user inputs directly using an if-else loop.
     */
    public String getResponse(String input) {
        String cleanInput = input.trim();
        Parser.Command command = parser.parseCommand(cleanInput);

        try {
            switch (command) {
            case BYE:
                commandType = "byeCommand";
                return "Bye! Hope to see you again soon.";

            case LIST:
                commandType = "listCommand";
                return listCommand();

            case ADD_TASK:
                commandType = "addCommand";
                return addCommand(cleanInput);

            case DELETE:
                commandType = "deleteCommand";
                return deleteCommand(cleanInput);

            case MARK:
                commandType = "changeMarkCommand";
                return markDoneCommand(cleanInput);

            case EMPTY:
                commandType = "unknownCommand";
                return "Error: Please enter a command.";

            default:
                commandType = "unknownCommand";
                return "Error: I am sorry, but I don't know what that means :-(";
            }
        } catch (DukeException e) {
            commandType = "errorCommand";
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    private String listCommand() {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        }

        return tasks.toString();
    }

    private String addCommand(String input) throws DukeException {
        String taskInput = input.substring("add task".length()).trim();

        if (taskInput.startsWith("todo")) {
            Task task = ToDo.fromCommand(taskInput);
            tasks.add(task);

            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (taskInput.startsWith("deadline")) {
            Task task = Deadlines.fromCommand(taskInput);
            tasks.add(task);

            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (taskInput.startsWith("event")) {
            Task task = Event.fromCommand(taskInput);
            tasks.add(task);

            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        }

        throw new DukeException(
                "Unknown task type. Please use todo, deadline, or event."
        );
    }

    private String markDoneCommand(String input) throws DukeException {
        Task task = tasks.markDone(input);

        return "Nice! I've marked this task as done:\n  " + task;
    }

    private String deleteCommand(String input) throws DukeException {
        Task removedTask = tasks.delete(input);

        return "Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}