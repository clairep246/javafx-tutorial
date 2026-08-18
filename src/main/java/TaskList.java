import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task t) { 
        tasks.add(t);
         }
    public Task delete(int index) { 
        return tasks.remove(index); 
    }
    public Task get(int index) { 
        return tasks.get(index); 
        }
    public int size() { 
        return tasks.size(); 
        }
    public boolean isEmpty() { 
        return tasks.isEmpty(); 
        }
}
