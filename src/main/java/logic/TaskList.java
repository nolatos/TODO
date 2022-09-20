package logic;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static TaskList instance = null;
    private List<Task> tasks = new ArrayList<logic.Task>();
    private TaskList() {

    }

    public TaskList getInstance() {
        if (instance == null) {
            this.instance = new TaskList();
        }
        return instance;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

}
