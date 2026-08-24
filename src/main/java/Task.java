/**
 * Represents the base template for all task types in the Bags application.
 *
 * <p>
 * This abstract class stores the common properties and behaviours
 * shared by different task types such as ToDo, Deadline, and Event.
 * </p>
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description text describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return task description text
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is marked done.
     *
     * @return {@code true} if the task is done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon for the task.
     *
     * <p>
     * An {@code X} represents a completed task, while a blank space
     * represents a task that has not been completed.
     * </p>
     *
     * @return the status icon of the task
     */
    protected String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a basic string representation of the task containing
     * its completion status and description.
     *
     * @return the task status and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}