import java.util.ArrayList;
import java.util.List;

/**
 * Stores tasks and provides operations that change the task collection.
 *
 */
public class Tasklist {
    private final ArrayList<Task> tasks;
    /**
     * Creates an empty task list.
     */
    public Tasklist() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the supplied loaded tasks.
     *
     * @param tasks the list of tasks to initialise the task list with
     */
    public Tasklist(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list and creates its corresponding
     * storage record.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return {@code true} if no tasks are stored
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a view of the stored tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Marks the selected task as done and updates its corresponding
     * storage record.
     *
     * @param output the user's mark command containing the task number
     * @return the task that was marked as done
     * @throws DukeException if the task number is missing, invalid,
     *                       or does not correspond to an existing task
     */
    public Task markDone(String output) throws DukeException {
        String[] temp = output.split(" ");

        if (temp.length < 2) {
            throw new DukeException(
                    "Missing task number. Add a number from 1 to " + tasks.size());
        }

        try {
            int taskNumber = Integer.parseInt(temp[1]);

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DukeException(
                        "Task does not exist. Please only input number 1 to "
                                + tasks.size());
            }

            Task task = tasks.get(taskNumber - 1);
            task.markDone();

            return task;
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "Invalid task number! Please enter a valid number from 1 to "
                            + tasks.size());
        }
    }

    /**
     * Deletes the task selected.
     *
     * @param output command string containing the task number
     * @return the deleted task
     * @throws DukeException if the task number is missing or invalid
     */
    public Task delete(String output) throws DukeException {
        String[] temp = output.split(" ");

        if (temp.length < 2) {
            throw new DukeException(
                    "Missing task number. Enter value from 1 to " + tasks.size());
        }

        try {
            int taskNumber = Integer.parseInt(temp[1]);

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DukeException(
                        "Task does not exist. Enter value from 1 to "
                                + tasks.size());
            }

            Task task = tasks.remove(taskNumber - 1);

            return task;
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "Invalid task number. Please enter a valid number from 1 to "
                            + tasks.size());
        }
    }

    /**
     * Returns the task list in a numbered format.
     *
     * @return formatted task list string
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            output.append(System.lineSeparator())
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i));
        }

        return output.toString();
    }

}