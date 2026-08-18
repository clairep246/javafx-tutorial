
public class Duke {
    private static final TaskList tasks = new TaskList();
    private String commandType;

    public Duke() {
        // Constructor
    }

    /**
     * Generates a response by matching user inputs directly using an if-else loop.
     */
    public String getResponse(String input) {
        String cleanInput = input.trim();
        String lowerInput = cleanInput.toLowerCase();

        // 1. BYE COMMAND
        if (lowerInput.equals("bye")) {
            return "Bye! Hope to see you again soon.";
        } 
        
        // 2. LIST COMMAND
        else if (lowerInput.equals("list")) {
            commandType = "listCommand";
            return listCommand();
        } 
        
        // 3. TODO COMMAND
        else if (lowerInput.startsWith("add")) {
            commandType = "addCommand";
            return addCommand(cleanInput.substring(4).trim());
        } 
        
        // 6. DELETE COMMAND
        else if (lowerInput.startsWith("delete ")) {
            commandType = "deleteCommand";
            return deleteCommand(cleanInput.substring(7).trim());
        } 

        else if(lowerInput.startsWith("mark ")){
            commandType = "changeMarkCommand";
            return markDoneCommand(cleanInput.substring(5).trim());
        }
        
        // 7. UNKNOWN INPUT FALLBACK
        else {
            commandType = "unknownCommand";
            return "Error: I am sorry, but I don't know what that means :-(";
        }
    }

    // ==========================================
    // COMMAND HELPER METHODS
    // ==========================================
    public String getCommandType() {
        return commandType;
    }
    private String listCommand() {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    private String addCommand(String description) {
        if (description.isEmpty()) {
            return "Error: The description of a todo cannot be empty.";
        }
        Task t = new Task(description);
        tasks.add(t);
        return "Got it. I've added this task:\n  " + t + 
               "\nNow you have " + tasks.size() + " tasks in the list.";
    }


    private String markDoneCommand(String textNumber) {
        try {
            int index = Integer.parseInt(textNumber) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "Error: Task number out of bounds!";
            }
            tasks.get(index).markDone();
            return "Nice! I've marked this task as done:\n  " + tasks.get(index);
        } catch (NumberFormatException e) {
            return "Error: Please specify a valid task number (e.g., mark 2).";
        }
    }

    private String deleteCommand(String textNumber) {
        try {
            int index = Integer.parseInt(textNumber) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "Error: Task number out of bounds!";
            }
            Task removedTask = tasks.delete(index);
            return "Noted. I've removed this task:\n  " + removedTask + 
                   "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (NumberFormatException e) {
            return "Error: Please specify a valid task number (e.g., delete 2).";
        }
    }
}
