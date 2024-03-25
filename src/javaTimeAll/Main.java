package javaTimeAll;

import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
    	    SharedData.initializeHost();
    	    SharedData.initializeVisitor();
    	    SharedData.initializeCode();
        runVisitorAccessManagementSystem();
    }

    private static void runVisitorAccessManagementSystem() {
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;

        System.out.println("Welcome to the Visitor Access Management System!");
        System.out.println();

        while (!endProgram) {
            String role = selectRole(scanner);
            switch (role) {
                case "H":
                    Host.handleHostActions();  //method in Host class
                    break;
                case "V":
                	Visitor.handleVisitorActions(); //method in Visitor class
                    break;
                case "A":
                	Admin.handleAdminActions();//method in Admin class
                    break;
                default:
                    System.out.println("Invalid role selection. "+"Action unavailable!!!");
            }

            endProgram = askToCloseProgram(scanner);
        }
        
        SharedData.saveHostFile();
        SharedData.saveVisitorFile();
        SharedData.saveCodeFile();

        scanner.close();
    }

    private static String selectRole(Scanner scanner) {
        System.out.println("Please select your role: ");
        System.out.println("Host: H");
        System.out.println("Visitor: V");
        System.out.println("Admin: A");
        return scanner.nextLine().toUpperCase();
    }


    private static boolean askToCloseProgram(Scanner scanner) {
        System.out.println("Would you like to close the program?");
        System.out.println("Yes: Y");
        System.out.println("No: N");
        String endOption = scanner.nextLine().toUpperCase();
        return endOption.equals("Y");
    }
}


