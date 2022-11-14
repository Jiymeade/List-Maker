import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<Integer> num = new ArrayList<>();
    private static Scanner pipe;
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer number = 2;
        num.add(9); //Adding values into the student names
        num.add(10);
        num.add(11);

        for (int value : num) { //displays the 3 names
            System.out.printf("%s ", value);
        }

        System.out.println("");


        String prompt = ("Which operation do you want to carry out the list A- add, D- delete, P - print, Q - quit");
        boolean done = false;
        do {
            displayList();
            String userInput = TSafeInput.SafeInput.getRegExString(in, prompt, "[AaDdPpQq]");
            userInput = userInput.toUpperCase();
            System.out.println(userInput);
            switch (userInput) {
                case "A":
                    num.add(1,TSafeInput.SafeInput.getRangedInt(in,"What number would you like to add to the list", 0, 12) );
                    for (int i = 0; i < num.size(); i++) {
                        System.out.printf("%s ", num.get(i));
                    }

                    System.out.println("");


                    break;
                case "D":
                    System.out.println("The size of the arraylist before removing:" + num.size());
                    num.remove(TSafeInput.SafeInput.getRangedInt(in, "What would you like to remove" , 0, 12));
                    for (int value : num) {
                        System.out.printf("%s ", value);
                    }
                    break;
                case "P":
                    for (int value : num) {
                        System.out.printf("%s ", value);
                    }
                    break;
                case "Q":
                    String quit = String.valueOf(TSafeInput.SafeInput.getYNConfirm(pipe,"Do you want to quit"));
                    break;
            }
        } while (!done);

    }

    private static void displayList() {
            System.out.print("**********************************************");
            if(list.size()!= 0) {

                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%3d%35s", i + 1, list.get(i));
                }
            }else{
                System.out.println("***********************************************");
            }
        }

    }
