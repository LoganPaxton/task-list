package com.task_list;

// Imports
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class App 
{
    public static void main( String[] args )
    {
        // Initialize a scanner object
        Scanner input = new Scanner(System.in);

        while (true) {
            // Print a prompt
            System.out.print(">> ");

            // Fetch a command
            String command = input.nextLine();

            // Debug Print the command
            //System.out.println("Command: " + command);

            if (command.startsWith("add")) {
                String[] split = command.split(" ");

                String to_add = split[1];
                System.out.println("Adding task: " + to_add);

                try {
                    FileWriter writer = new FileWriter("data.txt", true);
                    writer.append(to_add + "\n");
                    writer.close();
                    //System.out.println("Saved task successfully!");
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

                String to_complete = split[1];

                FileReader reader = new FileReader("data.txt");
                FileWriter writer = new FileWriter("data.txt");

                // Main removal logic here...

                reader.close();
                writer.close();
            } else {
                System.out.println("Invalid command!");
            }

            // Close the scanner to prevent memory leaks
        }
        input.close(); 
    }
}
