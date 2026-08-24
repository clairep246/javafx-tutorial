/**
 * Interprets user commands and converts saved task records back into tasks.
 *
 * <p>AI was used to assist in generating and improving the parser methods.
 * The generated code was reviewed and adapted to fit the application's
 * requirements.</p>
 */
public class Parser {

    /**
     * Commands recognized from user input.
     */
    public enum Command {
        ADD_TASK, LIST, MARK, UNMARK, DELETE, SEARCH, BYE, EMPTY, UNKNOWN
    }

    /**
     * Determines the type of command represented by the user's input.
     *
     * @param input the command entered by the user
     * @return the corresponding command type
     */
    public Command parseCommand(String input) {
        String command = input.trim().toLowerCase();

        if (command.isEmpty()) {
            return Command.EMPTY;
        } else if (command.startsWith("add task")) {
            return Command.ADD_TASK;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.startsWith("mark ")) {
            return Command.MARK;
        } else if (command.startsWith("unmark ")) {
            return Command.UNMARK;
        } else if (command.startsWith("search ")) {
            return Command.SEARCH;
        } else if (command.startsWith("delete ")) {
            return Command.DELETE;
        } else if (command.equals("bye")) {
            return Command.BYE;
        }

        return Command.UNKNOWN;
    }

  
}