import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.nio.file.Files.delete;
import static java.nio.file.StandardOpenOption.CREATE;
import static jdk.jfr.consumer.EventStream.openFile;

public class FileListMaker {
    File selectedFile;
    static ArrayList<String> list = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static boolean done = false;
    static boolean newUnsaveFile = false;
    static String menuPrompt = "A-add, D-delete, V-view O-open, S-save, C-clear, Q-quit";

    public static void main(String[] args) {

        do {
            String userInput = TSafeInput.SafeInput.getRegExString(in, menuPrompt, "[AaDdVvOoSsCcQq]");
            userInput = userInput.toUpperCase();
            System.out.println(userInput);
            switch (userInput) {
                case "A":
                    add();
                    break;
                case "D":
                    delete();
                    break;
                case "V":
                    view();
                    break;
                case "O":
                    openFile();
                    break;

                case "S":
                    save();
                    break;
                case "C":
                    clear();
                    break;

                default:

                    System.out.println("Invalid operation value entered:");
            }
        } while (!done);
    }

    private static void delete() {
        int itemToDelete = TSafeInput.SafeInput.getRangedInt(in, "Which item do you want to delete? ", 1, list.size());
        list.remove(itemToDelete - 1);
        System.out.println(itemToDelete + " was removed");
    }

    private static void add() {
        String itemToAdd = TSafeInput.SafeInput.getNonZeroLenString(in, "What would you like to add to the list? ");
        list.add(itemToAdd);

    }

    private static void save() {
        try {
            String fileName = TSafeInput.SafeInput.getNonZeroLenString(in, "Name the file that you want to save your list in");
            fileName = fileName + ".txt";
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                System.out.println("Saving list in file " + myFile.getName());
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(fileName));

                // Finally can write the file LOL!

                for(String rec : list)
                {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    private static void openFile() {
        try {
            JFileChooser chooser = new JFileChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();


                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                while (reader.ready()) {
                    String line = reader.readLine();
                    list.add(line);
                    list.addAll(Arrays.asList(line.split("")));  // read all the lines into memory in an array list
                    newUnsaveFile = true;

                }

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("File loaded Successfully");
                System.out.println(ListMaker.list);
            } else {
                System.out.println("Failed to choose a file");
                System.out.println("Run the program again");
                System.exit(0);


            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    

    private static void view() {
        if (list.size() != 0) {

            for (int i = 0; i < list.size(); i++) {
                System.out.printf("\n%-3d%-35s", i + 1, list.get(i));
            }
        } else
            System.out.println("List is empty");


    }

    private static void clear() {
        boolean needsSave = false;
        if (!needsSave) {
            list.clear();
            System.out.println("Your list has been cleared");
        } else {
            String userConfirm = TSafeInput.SafeInput.getYNConfirm(in, "Your list has not been saved yet. Clearing the list will terminate everything in your current list. Do you want to save your list first?");

            if (userConfirm.equalsIgnoreCase("y"))
                save();
            else if (userConfirm.equalsIgnoreCase("n")) {
                list.clear();
                System.out.println("Your list has been cleared");
            }
        }




    }
}


