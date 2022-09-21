package io;

import logic.Task;
import logic.TaskList;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TaskList taskList = TaskList.getInstance();
        System.out.println("App is open and working");
        System.out.println("Press 'D' to display tasks, press 'A' to add a task or 'Q' to quit: ");
        java.io.InputStreamReader stdin = new java.io.InputStreamReader(System.in);

        java.io.BufferedReader in = new java.io.BufferedReader(stdin);
        while (true) {
            try {
                String input = in.readLine();
                if (input.equals("D")) {
                    List<Task> tasks = taskList.getTasks();
                    for (Task task : tasks)  {
                        task.print();
                    }
                }
                else if (input.equals("A")) {
                    System.out.println("Type the name: ");
                    String name = in.readLine();
                    System.out.println("Type the due date (dd/mm/yyyy): ");
                    String dueDate = in.readLine();
                    System.out.println("Write a description: ");
                    String description = in.readLine();
                    taskList.addTask(new Task(name, dueDate, description));
                }
                else if (input.equals("Q")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
