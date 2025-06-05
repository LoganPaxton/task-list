package com.task_list;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class App 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");

            String command = input.nextLine();

            if (command.startsWith("add")) {
                String[] split = command.split(" ");

                String to_add = split[1];
                System.out.println("Adding task: " + to_add);

                try {
                    FileWriter writer = new FileWriter("data.txt", true);
                    writer.append(to_add + "\n");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("view tasks")) { 
                try {
                    FileReader reader = new FileReader("data.txt");

                    int i;

                    while ((i = reader.read()) != -1) {
                        System.out.print((char)i);
                    }

                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the file.");
                    e.printStackTrace();
                }
            } else if (command.startsWith("complete")) {
                String[] split = command.split(" ");

                if (split.length < 2) {
                    System.out.println("Please specify a task to complete.");
                    continue;
                }

                String to_complete = split[1];

                try {
                    FileReader reader = new FileReader("data.txt");
                    StringBuilder content = new StringBuilder();
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        content.append((char) ch);
                    }
                    reader.close();

                    String[] tasks = content.toString().split("\n");
                    StringBuilder updatedTasks = new StringBuilder();
                    for (String task : tasks) {
                        if (!task.trim().equals(to_complete)) {
                            updatedTasks.append(task).append("\n");
                        }
                    }

                    FileWriter writer = new FileWriter("data.txt");
                    writer.write(updatedTasks.toString());
                    writer.close();

                    System.out.println("Completed and removed task: " + to_complete);
                } catch (IOException e) {
                    System.out.println("An error occurred while completing the task.");
                    e.printStackTrace();
                }
            } else if (command.equals("clear")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else if (command.equals("help")) {
                System.out.println("Available Commands: ");
                System.out.println("- help: Shows this message");
                System.out.println("- exit: Exit the program");
                System.out.println("- clear: Clear the console");
                System.out.println("- complete <task_name>: Complete and remove a task");
                System.out.println("- view tasks: Shows all incomplete tasks");
                System.out.println("- add <task_name>: Add a new task to complete");
            } else {
                System.out.println("Invalid command: " + command);
            }
        }
        input.close(); 
    }
}
