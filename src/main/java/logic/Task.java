package logic;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Task {
    private final String name;
    private final String dueDate;
    private final String description;

    public Task(String name, String dueDate, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Map<String, Object> toData() {
        Map<String,Object> result = new HashMap<>();
        result.put("name", name);
        result.put("dueDate", dueDate);
        result.put("description", description);
        return result;
    }

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println("name: " + name + " due date: " + dueDate + " description: " + description);
    }
}
