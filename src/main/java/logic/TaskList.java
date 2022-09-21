package logic;

import database.DBHandler;

import java.util.*;

public class TaskList {

    private static TaskList instance = null;
    private DBHandler dbHandler = DBHandler.getInstance();
    private List<Task> tasks = new ArrayList<logic.Task>();
    private TaskList() {
        retrieveDataFromDB();
    }

    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.dbHandler.writeToData(task.toData(), "tasks", task.getName());
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves fresh data from DB.
     */
    private void retrieveDataFromDB() {
        List<Map<String, Object>> documents = dbHandler.getAllDocuments("tasks");
        this.tasks.clear();
        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String dueDate = (String) document.get("dueDate");
            String description = (String) document.get("description");
            tasks.add(new Task(name, dueDate, description));
        }
    }



}
